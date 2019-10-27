package com.qinyuan.ordersystem.service;

import com.qinyuan.ordersystem.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> getOrders();

    List<Order> getOrdersByName(String name);

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
