package com.ecomshop.mapper;

import com.ecomshop.dto.CartDto;
import com.ecomshop.dto.CartItemDto;
import com.ecomshop.dto.ImageDto;
import com.ecomshop.dto.ProductDto;
import com.ecomshop.model.Cart;
import com.ecomshop.model.CartItem;
import com.ecomshop.model.Image;
import com.ecomshop.model.Product;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CartMapper {

    public static CartDto toDto(Cart cart){
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setTotalAmount(cart.getTotalAmount());

        Set<CartItemDto> itemDtos = cart.getItems().stream()
                .map(CartMapper::toItemDto)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        cartDto.setItems(itemDtos);
        return cartDto;
    }

    private static CartItemDto toItemDto(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setItemId(cartItem.getId());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setUnitPrice(cartItem.getUnitPrice());

        Product product = cartItem.getProduct();
        if(product != null){
            cartItemDto.setProduct(toProductDto(product));
        }
        return cartItemDto;

    }

    private static ProductDto toProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setBrand(product.getBrand());
        productDto.setPrice(product.getPrice());
        productDto.setInventory(product.getInventory());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(product.getCategory());

        List<ImageDto> imageDtos = product.getImages().stream()
                .map(CartMapper::toImageDto)
                .collect(Collectors.toList());

        productDto.setImages(imageDtos);
        return productDto;
    }

    private static ImageDto toImageDto(Image image) {
        ImageDto imageDto = new ImageDto();
        imageDto.setId(image.getId());
        imageDto.setFileName(image.getFileName());
        imageDto.setDownloadUrl(image.getDownloadUrl());
        return  imageDto;
    }
}
