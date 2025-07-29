package com.ecomshop.service.order;

import com.ecomshop.model.Order;

import java.util.List;

public interface IOrderService {

    Order placeOrder(Long userId);

    Order getOrder(Long orderId);

    List<Order> getUserOrders(Long userId);
}
