package com.ecom.ecommerceapis.ResponseDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllOrdersResponse {
    private String productName;
    private String description;
    private double price;
    private int quantity;
    private String orderStatus;
    private String orderDate;
    private String deliveryDate;
    private String deliveryAddress;
    private String phoneNumber;
    private String email;
    private String name;

}
