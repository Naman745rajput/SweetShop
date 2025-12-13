package com.naman.SweetShop.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "sweet_id", nullable = false)
    private Sweet sweet;

    private Integer quantity;

    private BigDecimal totalPrice;

    @CreationTimestamp
    private LocalDateTime orderDate;

    public Order(User user, Sweet sweet, Integer quantity, BigDecimal totalPrice) {
        this.user = user;
        this.sweet = sweet;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
