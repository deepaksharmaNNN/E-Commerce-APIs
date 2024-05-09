package com.ecom.ecommerceapis.Repository;

import com.ecom.ecommerceapis.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //return all products of a seller
    List<Product> findBySellerId(Long sellerId);

}
