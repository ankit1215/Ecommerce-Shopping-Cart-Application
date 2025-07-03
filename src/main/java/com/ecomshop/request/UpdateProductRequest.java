package com.ecomshop.request;

import com.ecomshop.model.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProductRequest {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;  // we can also give it quantity inventory is quantity here
    private String description;
    private Category category;
}
