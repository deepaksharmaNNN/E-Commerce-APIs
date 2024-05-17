package com.ecom.ecommerceapis.Controller;

import com.ecom.ecommerceapis.RequestDTOs.AddProductRequest;
import com.ecom.ecommerceapis.Service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@NoArgsConstructor
@AllArgsConstructor
@Data
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    //create a product
    @PostMapping("/create")//http://localhost:8080/api/product/create
    public ResponseEntity<?> createProduct(@RequestBody AddProductRequest addProductRequest){
        try {
            String response = productService.createProduct(addProductRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
