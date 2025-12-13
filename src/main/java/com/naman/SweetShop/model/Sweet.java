package com.naman.SweetShop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name="sweets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;
    // e.g., "Candy", "Chocolate", "Pastry"
    private String description;

    private String imageUrl;  // store the link to the sweet's image
}
