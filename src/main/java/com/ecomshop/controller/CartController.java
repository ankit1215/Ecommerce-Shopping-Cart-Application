package com.ecomshop.controller;

import com.ecomshop.dto.CartDto;
import com.ecomshop.exception.ResourceNotFoundException;
import com.ecomshop.mapper.CartMapper;
import com.ecomshop.model.Cart;
import com.ecomshop.response.ApiResponse;
import com.ecomshop.service.cart.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/carts")
public class CartController {


    @Autowired
    private ICartService iCartService;

//    @GetMapping("/{cartId}/my-cart")
//    public ResponseEntity<ApiResponse> getCart( @PathVariable Long cartId) {
//        try {
//            Cart cart = iCartService.getCart(cartId);
//            return ResponseEntity.ok(new ApiResponse("Success", cart));
//        } catch (ResourceNotFoundException e) {
//            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
//        }
//    }

    @GetMapping("/{cartId}/my-cart")
    public ResponseEntity<ApiResponse> getCart(@PathVariable Long cartId) {
        try {
            Cart cart = iCartService.getCart(cartId); // Fetches full entity
            CartDto cartDto = CartMapper.toDto(cart); // Convert to DTO
            return ResponseEntity.ok(new ApiResponse("Success", cartDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }



    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<ApiResponse> clearCart(@PathVariable Long cartId) {
        try {
            iCartService.clearCart(cartId);
            return ResponseEntity.ok(new ApiResponse(" Clear cart success!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }


    @GetMapping("/{cartId}/cart/total-price")
    public ResponseEntity<ApiResponse> getTotalAmount(@PathVariable Long cartId) {
        try {
            BigDecimal totalPrice = iCartService.getTotalPrice(cartId);
            return ResponseEntity.ok(new ApiResponse("Total Price", totalPrice));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

}
