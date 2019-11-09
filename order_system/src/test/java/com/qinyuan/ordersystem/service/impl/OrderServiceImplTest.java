package com.qinyuan.ordersystem.service.impl;

import com.qinyuan.ordersystem.dao.OrderRepository;
import com.qinyuan.ordersystem.entity.Order;
import com.qinyuan.ordersystem.service.OrderService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Test
    @Ignore
    public void addOrder(){
        Order order=new Order();
        order.setName("Frank");
        order.setAddress("New York");
        order.setSoldDate(Date.valueOf("2018-2-15"));

        System.out.println(orderRepository.save(order));
    }

    @Test
    @Ignore
    public void deleteOrder(){
        System.out.println(orderService.deleteOrder(88));
    }

}