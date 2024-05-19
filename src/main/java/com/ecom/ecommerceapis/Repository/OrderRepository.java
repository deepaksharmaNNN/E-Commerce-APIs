package com.ecom.ecommerceapis.Repository;

import com.ecom.ecommerceapis.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
