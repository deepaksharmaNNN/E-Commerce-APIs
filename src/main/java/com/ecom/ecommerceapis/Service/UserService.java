package com.ecom.ecommerceapis.Service;

import com.ecom.ecommerceapis.Models.Cart;
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
                .phoneNumber(addUserRequest.getPhoneNumber())
                .address(addUserRequest.getAddress())
                .build();

        //create cart for the user
        Cart cart = new Cart();
        cart.setUser(user);
        user.setCart(cart);
        userRepository.save(user);

        // Create a Cart for all users
        //cartService.createCart(user.getId());

        return "User created successfully -> " + user.getId() + " and Cart created successfully -> " + user.getCart().getId();
    }
    public List<User> getBuyers(){
        return userRepository.findAll();
    }

}
