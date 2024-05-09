package com.ecom.ecommerceapis.Service;

import com.ecom.ecommerceapis.Models.User;
import com.ecom.ecommerceapis.Repository.UserRepository;
import com.ecom.ecommerceapis.RequestDTOs.AddUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

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
}
