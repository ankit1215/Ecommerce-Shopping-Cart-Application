package com.ecomshop.service.order;

import com.ecomshop.model.Order;

public interface IOrderService {

    Order placeOrder(Long userId);

    Order getOrder(Long orderId);
}
