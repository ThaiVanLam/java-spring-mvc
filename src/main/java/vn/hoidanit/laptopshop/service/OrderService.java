package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.OrderDetail;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.OrderDetailRepository;
import vn.hoidanit.laptopshop.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    public OrderService(OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
    }

    public Page<Order> getAllOrders(Pageable page) {
        return this.orderRepository.findAll(page);
    }

    public Order getOrderById(long id) {
        return this.orderRepository.findById(id).orElse(null);
    }

    public List<OrderDetail> getOrderDetailsByOrder(Order order) {
        return this.orderDetailRepository.findByOrder(order);
    }

    public void updateOrder(Order order) {
        this.orderRepository.save(order);
    }

    public void deleteOrder(Order order) {
        this.orderRepository.delete(order);
    }

    public void deleteOrderDetail(OrderDetail orderDetail) {
        this.orderDetailRepository.delete(orderDetail);
    }

    public List<Order> getOrdersByUser(User user) {
        return this.orderRepository.findByUser(user);
    }
}
