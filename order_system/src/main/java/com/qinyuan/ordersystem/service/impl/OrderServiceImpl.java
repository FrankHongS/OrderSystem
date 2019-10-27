package com.qinyuan.ordersystem.service.impl;

import com.qinyuan.ordersystem.dao.OrderRepository;
import com.qinyuan.ordersystem.entity.Order;
import com.qinyuan.ordersystem.exception.OrderException;
import com.qinyuan.ordersystem.service.OrderService;
import com.qinyuan.ordersystem.vo.ResultEnum;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository mOrderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.mOrderRepository = orderRepository;
    }

    @Override
    public List<Order> getOrders() {
        return mOrderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByName(String name) {
        return mOrderRepository.findOrdersByName(name);
    }

    @Override
    public Order addOrder(String name,
                          String phoneNumber,
                          String address,
                          String soldDate,
                          String machineType) {
        Order order = new Order();
        order.setName(name);
        order.setPhoneNumber(phoneNumber);
        order.setAddress(address);
        if (soldDate != null) {
            try {
                order.setSoldDate(Date.valueOf(soldDate));
            } catch (Exception e) {
                throw new OrderException(ResultEnum.DATE_FORMAT_INVALID);
            }
        }
        order.setMachineType(machineType);
        return mOrderRepository.save(order);
    }

    @Override
    public Order updateOrder(int id,
                             String name,
                             String phoneNumber,
                             String address,
                             String soldDate,
                             String machineType) {
        Optional<Order> optionalOrder = mOrderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order newOrder = new Order();
            if (name != null) {
                newOrder.setName(name);
            }
            if (phoneNumber != null) {
                newOrder.setPhoneNumber(phoneNumber);
            }
            if (address != null) {
                newOrder.setAddress(address);
            }
            if (soldDate != null) {
                try {
                    newOrder.setSoldDate(Date.valueOf(soldDate));
                } catch (Exception e) {
                    throw new OrderException(ResultEnum.DATE_FORMAT_INVALID);
                }
            }
            if (machineType != null) {
                newOrder.setMachineType(machineType);
            }
            return mOrderRepository.save(newOrder);
        } else {
            throw new OrderException(ResultEnum.ORDER_NOT_PRESENT);
        }
    }

    @Override
    public Order deleteOrder(int id) {
        Optional<Order> optionalOrder = mOrderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            mOrderRepository.delete(order);
            return order;
        } else {
            throw new OrderException(ResultEnum.ORDER_NOT_PRESENT);
        }
    }
}
