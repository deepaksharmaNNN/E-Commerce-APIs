package com.ecom.ecommerceapis.RequestDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PlaceOrderRequest {
    private long userId;
    private String shippingAddress;
    private String paymentMethod;
}
