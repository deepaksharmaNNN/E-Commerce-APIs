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
    //Update user phone number
    @PutMapping("/update/phone/{userId}")//http://localhost:8080/api/user/update/phone/1
    public ResponseEntity<?> updateUserPhoneNumber(@PathVariable Long userId, @RequestParam String phoneNumber){
        try{
            String response = userService.updateUserPhoneNumber(userId, phoneNumber);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //Update user Email
    @PutMapping("/update/email/{userId}")//http://localhost:8080/api/user/update/email/1
    public ResponseEntity<?> updateUserEmail(@PathVariable Long userId, @RequestParam String email){
        try{
            String response = userService.updateUserEmail(userId, email);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
/*
    //Delete a user
    @DeleteMapping("/delete/{userId}")//http://localhost:8080/api/user/delete/1
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        try{
            String response = userService.deleteUser(userId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
*/
}
