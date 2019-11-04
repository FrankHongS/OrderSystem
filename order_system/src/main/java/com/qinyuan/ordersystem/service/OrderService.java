package com.qinyuan.ordersystem.service;

import com.qinyuan.ordersystem.entity.Order;
import com.qinyuan.ordersystem.vo.PageItem;

import java.util.List;

public interface OrderService {

    List<Order> getOrders();

    PageItem<Order> getOrdersByPageNoCriteria(int page, int size);

    PageItem<Order> getOrdersByName(int page, int size, String name);

    Order addOrder(String name,
                   String phoneNumber,
                   String address,
                   String soldDate,
                   String machineType);

    Order updateOrder(int id,
                      String name,
                      String phoneNumber,
                      String address,
                      String soldDate,
                      String machineType);

    Order deleteOrder(int id);

}
