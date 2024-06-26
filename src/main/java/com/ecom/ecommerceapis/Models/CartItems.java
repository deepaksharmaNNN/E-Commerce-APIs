package com.ecom.ecommerceapis.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart_items")
@Builder
@Entity
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    private Long productId;
    private String productName;
    private String description;
    private double price;
    private int quantity;


    @JoinColumn
    @ManyToOne
    private Cart cart;
}
