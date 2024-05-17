package com.ecom.ecommerceapis.Controller;

import com.ecom.ecommerceapis.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    //get the list of Sellers
    @GetMapping("/sellers")//http://localhost:8080/api/user/sellers
    public ResponseEntity<?> getSellers(){
        try {
            return new ResponseEntity<>(sellerService.getSellers(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //get the list of Products added by a Seller
    @GetMapping("/seller/{sellerId}")//http://localhost:8080/api/user/seller/{sellerId}
    public ResponseEntity<?> getSellerProducts(@PathVariable Long sellerId){
        try {
            return new ResponseEntity<>(sellerService.getSellerProducts(sellerId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
