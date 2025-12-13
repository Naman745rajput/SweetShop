package com.naman.SweetShop.controller;

import com.naman.SweetShop.dto.OrderRequest;
import com.naman.SweetShop.model.Order;
import com.naman.SweetShop.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest request, Principal principal) {
        Order newOrder = orderService.placeOrder(
                principal.getName(),
                request.getSweetId(),
                request.getQuantity()
        );
        return ResponseEntity.ok(newOrder);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getMyOrders(Principal principal) {
        return ResponseEntity.ok(orderService.getUserOrders(principal.getName()));
    }
}
