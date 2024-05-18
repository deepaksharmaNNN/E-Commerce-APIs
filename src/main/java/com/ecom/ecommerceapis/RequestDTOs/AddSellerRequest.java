package com.ecom.ecommerceapis.RequestDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddSellerRequest {
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
}
