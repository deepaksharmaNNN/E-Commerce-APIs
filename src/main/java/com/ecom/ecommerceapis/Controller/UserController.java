package com.ecom.ecommerceapis.Controller;

import com.ecom.ecommerceapis.RequestDTOs.AddUserRequest;
import com.ecom.ecommerceapis.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@Data
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")//http://localhost:8080/api/user/create
    public ResponseEntity<?> createUser(@RequestBody AddUserRequest addUserRequest){
        try {
            String response = userService.createUser(addUserRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //get the list of Buyers
    @GetMapping("/buyers")//http://localhost:8080/api/user/buyers
    public ResponseEntity<?> getBuyers(){
        try {
            return new ResponseEntity<>(userService.getBuyers(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //get the list of Sellers
    @GetMapping("/sellers")//http://localhost:8080/api/user/sellers
    public ResponseEntity<?> getSellers(){
        try {
            return new ResponseEntity<>(userService.getSellers(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //get the list of Products added by a Seller
    @GetMapping("/seller/{sellerId}")//http://localhost:8080/api/user/seller/{sellerId}
    public ResponseEntity<?> getSellerProducts(@PathVariable Long sellerId){
        try {
            return new ResponseEntity<>(userService.getSellerProducts(sellerId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
