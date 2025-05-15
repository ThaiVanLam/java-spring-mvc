package vn.hoidanit.laptopshop.controller.client;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.OrderService;
import vn.hoidanit.laptopshop.service.ProductService;

@Controller
public class HomePageController {

    private final ProductService productService;
    private final OrderService orderService;

    public HomePageController(ProductService productService, OrderService orderService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Product> products = productService.getAllProducts(pageable);
        List<Product> listProducts = products.getContent();
        model.addAttribute("products", listProducts);
        return "client/homepage/show";
    }

    @GetMapping("/access-denied")
    public String getDeniedPage() {
        return "client/auth/denied";
    }

    @GetMapping("/order-history")
    public String getOrderHistorypage(Model model, HttpServletRequest request) {
        User currentUser = new User();
        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("id");
        currentUser.setId(id);
        // get all order of user
        List<Order> orders = this.orderService.getOrdersByUser(currentUser);
        model.addAttribute("orders", orders);
        return "client/order_history/show";
    }

}
