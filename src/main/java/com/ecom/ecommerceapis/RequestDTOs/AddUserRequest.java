package com.ecom.ecommerceapis.RequestDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddUserRequest {
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
}
