package com.ecom.ecommerceapis.ResponseDTOs;

import com.ecom.ecommerceapis.Enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductsSellerResponse {
    private String name;
    private String description;
    private ProductType productType;
    private double price;
    private int quantity;
}
