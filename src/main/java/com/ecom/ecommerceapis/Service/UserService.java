package com.ecom.ecommerceapis.Service;

import com.ecom.ecommerceapis.Enums.UserType;
import com.ecom.ecommerceapis.Models.Product;
import com.ecom.ecommerceapis.Models.User;
import com.ecom.ecommerceapis.Repository.ProductRepository;
import com.ecom.ecommerceapis.Repository.UserRepository;
import com.ecom.ecommerceapis.RequestDTOs.AddUserRequest;
import com.ecom.ecommerceapis.ResponseDTOs.ProductsSellerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartService cartService;

    public String createUser(AddUserRequest addUserRequest) {
        User user = User.builder()
                .name(addUserRequest.getName())
                .email(addUserRequest.getEmail())
                .userType(addUserRequest.getUserType())
                .phoneNumber(addUserRequest.getPhoneNumber())
                .address(addUserRequest.getAddress())
                .build();
        userRepository.save(user);

        // Create a Cart for all users
        cartService.createCart(user.getId());

        return "User created successfully -> " + user.getId() + " and Cart created successfully -> " + user.getCart().getId();
    }
    public List<User> getBuyers(){
        return userRepository.findByUserType(UserType.BUYER);
    }
    public List<User> getSellers(){
        return userRepository.findByUserType(UserType.SELLER);
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
