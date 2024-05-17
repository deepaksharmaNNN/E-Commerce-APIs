package com.ecom.ecommerceapis.ResponseDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductsInCartResponse {
    private String productName;
    private String description;
    private double price;
    private int quantity;

}
