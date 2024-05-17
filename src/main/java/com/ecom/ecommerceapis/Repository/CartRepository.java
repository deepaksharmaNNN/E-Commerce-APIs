package com.ecom.ecommerceapis.Repository;

import com.ecom.ecommerceapis.Models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    //find cart by user id
    Cart findByUserId(Long userId);
}
