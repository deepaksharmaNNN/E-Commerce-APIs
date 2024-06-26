package com.ecom.ecommerceapis.Service;

import com.ecom.ecommerceapis.Enums.OrderStatus;
import com.ecom.ecommerceapis.Enums.PaymentMethod;
import com.ecom.ecommerceapis.Models.*;
import com.ecom.ecommerceapis.Repository.CartRepository;
import com.ecom.ecommerceapis.Repository.OrderRepository;
import com.ecom.ecommerceapis.Repository.ProductRepository;
import com.ecom.ecommerceapis.Repository.UserRepository;
import com.ecom.ecommerceapis.RequestDTOs.PlaceOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public String placeOrder(PlaceOrderRequest placeOrderRequest){
        //Get the user from the user id
        User user = userRepository.findById(placeOrderRequest.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        //Retrieve the products from the cart
        List<CartItems> cartItems = cartRepository.findByUser(user).getCartItems();
        //Create an order
        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setTotalAmount(cartRepository.findByUser(user).getTotalAmount());
        order.setShippingAddress(placeOrderRequest.getShippingAddress());
        order.setPaymentMethod(PaymentMethod.valueOf(placeOrderRequest.getPaymentMethod()));
        order.setUser(user);

        //Map the cart items to the order items
        List<OrderItem> orderItems = new ArrayList<>();
        for(CartItems cartItem : cartItems){
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(cartItem.getProductId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setOrder(order);

            //Update the product quantity
            Product product = productRepository.findById(cartItem.getProductId()).orElse(null);
            if(product != null){
                product.setQuantity(product.getQuantity() - cartItem.getQuantity());
                productRepository.save(product);
            }
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);




        //SAVE THE ORDER
        orderRepository.save(order);

        //clear the cart
        cartItems.clear();
        Cart cart = cartRepository.findByUser(user);
        cart.setCartItems(cartItems);
        cart.setTotalAmount(0.0);
        cartRepository.save(cart);
        return "Order placed successfully -> " + order.getId();
    }

    //Cancel order
    public String cancelOrder(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        //Check if the order is already cancelled
        if(order.getOrderStatus().equals(OrderStatus.CANCELLED)){
            return "Order already cancelled";
        }
        //Check if the order is already delivered
        if(order.getOrderStatus().equals(OrderStatus.DELIVERED)){
            return "Order already delivered";
        }
        //Cancel the order
        order.setOrderStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
        //Update the product quantity
        List<OrderItem> orderItems = order.getOrderItems();
        for(OrderItem orderItem : orderItems){
            Product product = productRepository.findById(orderItem.getProductId()).orElse(null);
            if(product != null){
                product.setQuantity(product.getQuantity() + orderItem.getQuantity());
                productRepository.save(product);
            }
        }

        return "Order cancelled successfully";
    }
    //set order is shipped

}
