package com.qinyuan.ordersystem.service;

import com.qinyuan.ordersystem.entity.Order;
import com.qinyuan.ordersystem.entity.PostSoldCondition;
import com.qinyuan.ordersystem.vo.PageItem;

import java.sql.Date;
import java.util.List;

public interface PostSoldService {

    PageItem<Order> getAllTodoOrders(int page, int size);

    PageItem<Order> getAllTodoOrdersByName(int page, int size, String name);

    List<PostSoldCondition> getPostSoldConditions(int orderId);

    PostSoldCondition addPostSold(
            int orderId,
            Date todoDate,
            int status
    );

    PostSoldCondition updatePostSold(
            int id,
            int status,
            String comment
    );

    PostSoldCondition deletePostSold(
            int id
    );
}
