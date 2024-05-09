package com.ecom.ecommerceapis.Models;

import com.ecom.ecommerceapis.Enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private String phoneNumber;
    private String address;
}
