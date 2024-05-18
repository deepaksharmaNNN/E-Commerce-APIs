package com.ecom.ecommerceapis.Controller;

import com.ecom.ecommerceapis.RequestDTOs.AddProductToCartRequest;
import com.ecom.ecommerceapis.ResponseDTOs.ProductsInCartResponse;
import com.ecom.ecommerceapis.Service.CartService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("create")//http://localhost:8080/api/cart/create
    public ResponseEntity<?> createCart(@RequestParam Long userId){
        try {
            String response = cartService.createCart(userId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //add a product to the cart
    @PostMapping("/add")//http://localhost:8080/api/cart/add
    public ResponseEntity<?> addToCart(@RequestBody AddProductToCartRequest addProductToCartRequest){
        try {
            String response = cartService.addToCart(addProductToCartRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //remove a product from the cart by id
    @DeleteMapping("/remove/{cartItemId}")//http://localhost:8080/api/cart/remove/{cartItemId}
    public ResponseEntity<?> removeFromCart(@PathVariable Long cartItemId){
        try {
            String response = cartService.removeFromCart(cartItemId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //get the list of products in the cart
    @GetMapping("/products/{userId}")//http://localhost:8080/api/cart/products/{userId}
    public List<ProductsInCartResponse> getCartProducts(@PathVariable Long userId){
        return cartService.getCartProducts(userId);
    }

}
