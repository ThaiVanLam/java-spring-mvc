package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.OrderDetail;
import vn.hoidanit.laptopshop.domain.Order_;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.OrderService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/admin/order")
    public String getDashboard(Model model, @RequestParam("page") Optional<String> pageOptional) {

        int page = 1;
        if (pageOptional.isPresent()) {
            try {
                page = Integer.parseInt(pageOptional.get());
            } catch (Exception e) {
            }
        }
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(Order_.ID).descending());
        Page<Order> orders = orderService.getAllOrders(pageable);
        List<Order> listOrders = orders.getContent();
        model.addAttribute("orders", listOrders);

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", orders.getTotalPages());
        return "admin/order/show";
    }

    @GetMapping("/admin/order/{id}")
    public String getDetailPage(@PathVariable long id, Model model) {
        Order order = orderService.getOrderById(id);
        List<OrderDetail> orderDetails = orderService.getOrderDetailsByOrder(order);
        model.addAttribute("orderDetails", orderDetails);
        return "admin/order/detail";
    }

    @GetMapping("/admin/order/update/{id}")
    public String getUpdatePage(@PathVariable long id, Model model) {
        Order order = orderService.getOrderById(id);
        model.addAttribute("currentOrder", order);
        return "admin/order/update";
    }

    @PostMapping("/admin/order/update")
    public String postUpdateOrder(@ModelAttribute("currentOrder") Order order) {
        Order currentOrder = orderService.getOrderById(order.getId());
        currentOrder.setStatus(order.getStatus());
        this.orderService.updateOrder(currentOrder);
        return "redirect:/admin/order";
    }

    @GetMapping("/admin/order/delete/{id}")
    public String getDeletePage(@PathVariable long id, Model model) {
        Order order = orderService.getOrderById(id);
        model.addAttribute("currentOrder", order);
        return "admin/order/delete";
    }

    @PostMapping("/admin/order/delete")
    public String postDeleteOrder(@ModelAttribute("currentOrder") Order order) {
        Order currentOrder = orderService.getOrderById(order.getId());
        // delete order details first
        List<OrderDetail> orderDetails = orderService.getOrderDetailsByOrder(currentOrder);
        for (OrderDetail orderDetail : orderDetails) {
            orderService.deleteOrderDetail(orderDetail);
        }
        // delete order
        this.orderService.deleteOrder(currentOrder);
        return "redirect:/admin/order";
    }

}