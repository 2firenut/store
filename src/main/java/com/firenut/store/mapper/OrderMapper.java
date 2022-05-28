package com.firenut.store.mapper;

import com.firenut.store.entity.Order;
import com.firenut.store.entity.OrderItem;

public interface OrderMapper {
    Integer insertOrder(Order order);

    Integer insertOrderItem(OrderItem orderItem);
}
