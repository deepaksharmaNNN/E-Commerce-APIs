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
        //check if the user exists and has a cart
        Long userId = addProductToCartRequest.getUserId();
        Optional<Cart> cart = cartRepository.findById(userId);
        if(cart.isPresent()){
            //check if the product exists
            Long productId = addProductToCartRequest.getProductId();
            Optional<Product> product = productRepository.findById(productId);
            if(product.isPresent()){
                //check if the product is available
                if(product.get().getQuantity() >= addProductToCartRequest.getQuantity()){
                    //check if the product is already in the cart
                    List<CartItems> cartItems = cart.get().getCartItems();
                    for(CartItems cartItem : cartItems){
                        if(cartItem.getProductId().equals(productId)){
                            //update the quantity of the product in the cart
                            cartItem.setQuantity(cartItem.getQuantity() + 1);
                            cartRepository.save(cart.get());
                            return "Product added to cart successfully";
                        }else {
                            //add the product to the cart
                            CartItems newCartItem = new CartItems();
                            newCartItem.setCart(cart.get());
                            newCartItem.setProductId(productId);
                            newCartItem.setQuantity(addProductToCartRequest.getQuantity());
                            cartItemsRepository.save(newCartItem);
                            cartItems.add(newCartItem);
                            cartRepository.save(cart.get());
                            userRepository.save(cart.get().getUser());
                            return "Product added to cart successfully";
                        }
                    }
                }else {
                    return product.get().getName() + " is out of stock";
                }
            }else{
                return "Product not found";
            }
        }else{
            return "Cart not found";
        }
        return "Product added to cart successfully";
    }

    //get the list of products in the cart
    public List<ProductsInCartResponse> getCartProducts(Long userId){
        Optional<Cart> cart = cartRepository.findById(userId);
        List<ProductsInCartResponse> productsInCartResponses = new ArrayList<>();
        if(cart.isPresent()){
            List<CartItems> cartItems = cart.get().getCartItems();
            for(CartItems cartItem : cartItems){
                ProductsInCartResponse productsInCartResponse = ProductsInCartResponse.builder()
                        .productName(cartItem.getProductName())
                        .description(cartItem.getDescription())
                        .quantity(cartItem.getQuantity())
                        .price(cartItem.getPrice())
                        .build();
            }
        }
        return productsInCartResponses;
    }
}
