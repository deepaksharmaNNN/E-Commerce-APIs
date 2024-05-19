package com.ecom.ecommerceapis.Repository;

import com.ecom.ecommerceapis.Models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
