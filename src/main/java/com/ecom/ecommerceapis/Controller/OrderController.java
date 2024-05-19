package com.ecom.ecommerceapis.Controller;

import com.ecom.ecommerceapis.RequestDTOs.PlaceOrderRequest;
import com.ecom.ecommerceapis.Service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@Data
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")//http://localhost:8080/api/order/place
    public ResponseEntity<?> placeOrder(@RequestBody PlaceOrderRequest placeOrderRequest){
        try {
            String response = orderService.placeOrder(placeOrderRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
