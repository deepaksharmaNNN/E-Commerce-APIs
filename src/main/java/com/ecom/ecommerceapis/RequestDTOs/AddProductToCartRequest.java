package com.ecom.ecommerceapis.RequestDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AddProductToCartRequest {
    private Long productId;
    private int quantity;
    private Long userId;
}
