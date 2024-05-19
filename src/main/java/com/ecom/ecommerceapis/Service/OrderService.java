package com.ecom.ecommerceapis.Service;

import com.ecom.ecommerceapis.Enums.OrderStatus;
import com.ecom.ecommerceapis.Models.CartItems;
import com.ecom.ecommerceapis.Models.Order;
import com.ecom.ecommerceapis.Models.User;
import com.ecom.ecommerceapis.Repository.CartRepository;
import com.ecom.ecommerceapis.Repository.OrderRepository;
import com.ecom.ecommerceapis.Repository.UserRepository;
import com.ecom.ecommerceapis.RequestDTOs.PlaceOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    public String placeOrder(PlaceOrderRequest placeOrderRequest){
        //Get the user from the user id
        User user = userRepository.findById(placeOrderRequest.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        //Retrieve the products from the cart
        List<CartItems> cartItems = cartRepository.findByUser(user).getCartItems();
        //Create an order
        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setOrderStatus(OrderStatus.PENDING);

        return "Order placed successfully";
    }
}
