package com.ecom.ecommerceapis.Service;

import com.ecom.ecommerceapis.Models.Product;
import com.ecom.ecommerceapis.Repository.ProductRepository;
import com.ecom.ecommerceapis.Repository.SellerRepository;
import com.ecom.ecommerceapis.ResponseDTOs.ProductsSellerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerRepository sellerRepository;

    public String getSellers(){
        return sellerRepository.findAll().toString();
    }
    public List<ProductsSellerResponse> getSellerProducts(Long sellerId) {
        List<ProductsSellerResponse> productsSellerResponses = new ArrayList<>();
        List<Product> products = productRepository.findBySellerId(sellerId);
        for (Product product : products) {
            productsSellerResponses.add(ProductsSellerResponse.builder()
                    .name(product.getName())
                    .description(product.getDescription())
                    .productType(product.getProductType())
                    .price(product.getPrice())
                    .quantity(product.getQuantity())
                    .build());
        }
        return productsSellerResponses;
    }
}
