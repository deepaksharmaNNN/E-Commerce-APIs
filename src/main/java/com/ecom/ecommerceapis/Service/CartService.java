package com.ecom.ecommerceapis.Service;

import com.ecom.ecommerceapis.Models.Cart;
import com.ecom.ecommerceapis.Models.CartItems;
import com.ecom.ecommerceapis.Models.Product;
import com.ecom.ecommerceapis.Models.User;
import com.ecom.ecommerceapis.Repository.CartItemsRepository;
import com.ecom.ecommerceapis.Repository.CartRepository;
import com.ecom.ecommerceapis.Repository.ProductRepository;
import com.ecom.ecommerceapis.Repository.UserRepository;
import com.ecom.ecommerceapis.RequestDTOs.AddProductToCartRequest;
import com.ecom.ecommerceapis.ResponseDTOs.ProductsInCartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private UserRepository userRepository;

    public String createCart(Long userId){
        Cart cart = new Cart();
        User user = userRepository.findById(userId).get();
        cart.setUser(user);
        cart.setCartItems(new ArrayList<>());
        cartRepository.save(cart);
        return "Cart created successfully -> " + cart.getId();
    }

    public String addToCart(AddProductToCartRequest addProductToCartRequest){
        //get the user
        User user = userRepository.findById(addProductToCartRequest.getUserId()).orElse(null);
        if (user == null) {
            return "User not found";
        }
        //get the cart
        Cart cart = cartRepository.findByUser(user);
        if (cart == null) {
            return "Cart not found";
        }
        //get the product
        Product product = productRepository.findById(addProductToCartRequest.getProductId()).orElse(null);
        if (product == null) {
            return "Product not found";
        }
        //check if the product is already in the cart
        List<CartItems> cartItems = cart.getCartItems();
        for(CartItems cartItem : cartItems){
            if(cartItem.getProductName().equals(product.getName())){
                cartItem.setQuantity(cartItem.getQuantity() + addProductToCartRequest.getQuantity());
                cartItem.setPrice(cartItem.getPrice() + (product.getPrice() * addProductToCartRequest.getQuantity()));
                cartItemsRepository.save(cartItem);
                //return product message with current product quantity in cart
                return "Product already in cart, quantity updated to -> " + cartItem.getQuantity();
            }
        }
        //add the product to the cart
        CartItems cartItem = new CartItems();
        cartItem.setProductName(product.getName());
        cartItem.setDescription(product.getDescription());
        cartItem.setQuantity(addProductToCartRequest.getQuantity());
        cartItem.setPrice(product.getPrice() * addProductToCartRequest.getQuantity());
        cartItem.setCart(cart);
        cartItem.setProductId(product.getId());
        cartItemsRepository.save(cartItem);
        cartItems.add(cartItem);
        cart.setCartItems(cartItems);
        cartRepository.save(cart);
        //return message with product quantity in cart
        return "Product added to cart, quantity -> " + addProductToCartRequest.getQuantity();

    }

    //get the list of products in the cart
    public List<ProductsInCartResponse> getCartProducts(Long userId){
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return new ArrayList<>();
        }
        Cart cart = cartRepository.findByUser(user);
        List<ProductsInCartResponse> productsInCartResponses = new ArrayList<>();
        List<CartItems> cartItems = cart.getCartItems();
        for(CartItems cartItem : cartItems){
            ProductsInCartResponse productsInCartResponse = ProductsInCartResponse.builder()
                    .productName(cartItem.getProductName())
                    .description(cartItem.getDescription())
                    .quantity(cartItem.getQuantity())
                    .price(cartItem.getPrice())
                    .build();
            productsInCartResponses.add(productsInCartResponse);
        }
        return productsInCartResponses;
    }
}
