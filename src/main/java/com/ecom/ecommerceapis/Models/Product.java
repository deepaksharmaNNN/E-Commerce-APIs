package com.ecom.ecommerceapis.Models;

import com.ecom.ecommerceapis.Enums.ProductType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private ProductType productType;
    private double price;
    private int quantity;
    private Long sellerId;


    @ManyToOne
    @JoinColumn
    private User user;
}
