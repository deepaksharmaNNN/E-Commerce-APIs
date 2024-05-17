package com.ecom.ecommerceapis.Repository;

import com.ecom.ecommerceapis.Models.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemsRepository extends JpaRepository<CartItems, Long> {

}
