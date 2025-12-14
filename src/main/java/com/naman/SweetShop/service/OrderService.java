package com.naman.SweetShop.service;

import com.naman.SweetShop.dto.OrderRequest;
import com.naman.SweetShop.model.Order;
import com.naman.SweetShop.model.Sweet;
import com.naman.SweetShop.model.User;
import com.naman.SweetShop.repo.OrderRepository;
import com.naman.SweetShop.repo.SweetRepository;
import com.naman.SweetShop.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final SweetRepository sweetRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, SweetRepository sweetRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.sweetRepository = sweetRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Order placeOrder(String username, Long sweetId, Integer quantity) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
        Sweet sweet = sweetRepository.findById(sweetId)
                .orElseThrow(() -> new RuntimeException("Sweet not found with id: " + sweetId));

        int currentStock = (sweet.getQuantity() == null) ? 0 : sweet.getQuantity();

        if (currentStock < quantity) {
            throw new RuntimeException("Not enough stock for item: " + sweet.getName());
        }

        sweet.setQuantity(currentStock - quantity);
        sweetRepository.save(sweet);

        BigDecimal total = sweet.getPrice().multiply(BigDecimal.valueOf(quantity));
        Order order = new Order(user, sweet, quantity, total);
        return orderRepository.save(order);
    }

    public List<Order> getUserOrders(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return orderRepository.findByUser(user);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public List<Order> checkout(String username, List<OrderRequest> cartItems) {
        List<Order> orders = new ArrayList<>();

        for (OrderRequest item : cartItems) {
            Order order = placeOrder(username, item.getSweetId(), item.getQuantity());
            orders.add(order);
        }
        return orders;
    }
}
