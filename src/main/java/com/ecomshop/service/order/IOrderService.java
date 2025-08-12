package com.ecomshop.service.order;

import com.ecomshop.dto.OrderDto;
import com.ecomshop.model.Order;

import java.util.List;

public interface IOrderService {

    Order placeOrder(Long userId);

    OrderDto getOrder(Long orderId);

    List<OrderDto> getUserOrders(Long userId);
}
