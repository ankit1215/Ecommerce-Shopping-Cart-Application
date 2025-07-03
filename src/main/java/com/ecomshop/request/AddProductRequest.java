package com.ecomshop.request;

import com.ecomshop.model.Category;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddProductRequest {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;  // we can also give it quantity inventory is quantity here
    private String description;
    private Category category;

    public AddProductRequest(String name, Category category, String brand, BigDecimal price, int inventory, String description) {
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.inventory = inventory;
        this.description = description;
    }
}
