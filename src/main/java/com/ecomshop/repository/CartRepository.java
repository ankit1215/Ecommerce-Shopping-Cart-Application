package com.ecomshop.repository;

import com.ecomshop.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public interface CartRepository extends JpaRepository<Cart, Long>  {

    Cart findByUserId(Long userId);
}
