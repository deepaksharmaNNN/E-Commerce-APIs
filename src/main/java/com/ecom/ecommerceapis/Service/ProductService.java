package com.ecom.ecommerceapis.Service;

import com.ecom.ecommerceapis.Models.Product;
import com.ecom.ecommerceapis.Repository.ProductRepository;
import com.ecom.ecommerceapis.Repository.SellerRepository;
import com.ecom.ecommerceapis.RequestDTOs.AddProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerRepository sellerRepository;

    public String createProduct(AddProductRequest addProductRequest){
        Product product = Product.builder()
                .name(addProductRequest.getName())
                .description(addProductRequest.getDescription())
                .productType(addProductRequest.getProductType())
                .price(addProductRequest.getPrice())
                .quantity(addProductRequest.getQuantity())
                .build();
        sellerRepository.findById(addProductRequest.getSellerId()).ifPresent(product::setSeller);
        productRepository.save(product);
        return "Product created successfully -> " + product.getId();
    }
}
