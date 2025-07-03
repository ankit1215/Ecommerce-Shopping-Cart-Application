package com.ecomshop.service.product;

import com.ecomshop.model.Product;
import com.ecomshop.request.AddProductRequest;
import com.ecomshop.request.UpdateProductRequest;

import java.util.List;

public interface IProductService {

    Product addProduct(AddProductRequest  product);

    Product getProductById(Long id);

    void deleteProductById(Long id);

    Product updateProduct(UpdateProductRequest product, Long productId);

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByBrand(String brand);

    List<Product> getProductsByCategoryAndBrand(String category, String brand);

    List<Product> getProductsByName(String name);

    List<Product> getProductsByBrandAndName(String category, String name);

    Long countProductsByBrandAndName(String brand, String name);




}
