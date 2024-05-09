package com.ecom.ecommerceapis.Service;

import com.ecom.ecommerceapis.Enums.UserType;
import com.ecom.ecommerceapis.Models.Product;
import com.ecom.ecommerceapis.Models.User;
import com.ecom.ecommerceapis.Repository.ProductRepository;
import com.ecom.ecommerceapis.Repository.UserRepository;
import com.ecom.ecommerceapis.RequestDTOs.AddUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public String createUser(AddUserRequest addUserRequest){
        User user = User.builder()
                .name(addUserRequest.getName())
                .email(addUserRequest.getEmail())
                .userType(addUserRequest.getUserType())
                .phoneNumber(addUserRequest.getPhoneNumber())
                .address(addUserRequest.getAddress())
                .build();
        userRepository.save(user);
        return "User created successfully -> " + user.getId();
    }
    public List<User> getBuyers(){
        return userRepository.findByUserType(UserType.BUYER);
    }
    public List<User> getSellers(){
        return userRepository.findByUserType(UserType.SELLER);
    }
    public List<Product> getSellerProducts(Long sellerId){
        return productRepository.findBySellerId(sellerId);
    }
}
