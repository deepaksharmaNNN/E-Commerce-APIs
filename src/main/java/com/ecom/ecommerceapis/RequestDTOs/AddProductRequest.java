package com.ecom.ecommerceapis.RequestDTOs;

import com.ecom.ecommerceapis.Enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddProductRequest {
    private String name;
    private String description;
    private ProductType productType;
    private double price;
    private int quantity;
    private Long sellerId;
}
