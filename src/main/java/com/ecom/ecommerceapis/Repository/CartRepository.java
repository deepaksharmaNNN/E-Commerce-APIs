package com.ecom.ecommerceapis.Repository;

import com.ecom.ecommerceapis.Models.Cart;
import com.ecom.ecommerceapis.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    //find cart by user
    Cart findByUser(User user);
}
