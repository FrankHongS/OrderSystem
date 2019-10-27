package com.qinyuan.ordersystem.dao;

import com.qinyuan.ordersystem.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    List<Order> findOrdersByName(String name);


}
