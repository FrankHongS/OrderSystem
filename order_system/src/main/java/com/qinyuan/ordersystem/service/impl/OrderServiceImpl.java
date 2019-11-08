package com.qinyuan.ordersystem.service.impl;

import com.qinyuan.ordersystem.dao.OrderRepository;
import com.qinyuan.ordersystem.dao.PostSoldConditionRepository;
import com.qinyuan.ordersystem.dao.RecentDateRepository;
import com.qinyuan.ordersystem.entity.Order;
import com.qinyuan.ordersystem.entity.RecentDate;
import com.qinyuan.ordersystem.exception.OrderException;
import com.qinyuan.ordersystem.service.OrderService;
import com.qinyuan.ordersystem.vo.PageItem;
import com.qinyuan.ordersystem.vo.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository mOrderRepository;
    private final RecentDateRepository mRecentDateRepository;
    private final PostSoldConditionRepository mPostSoldConditionRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    public OrderServiceImpl(OrderRepository orderRepository, RecentDateRepository recentDateRepository, PostSoldConditionRepository postSoldConditionRepository) {
        this.mOrderRepository = orderRepository;
        this.mRecentDateRepository = recentDateRepository;
        this.mPostSoldConditionRepository = postSoldConditionRepository;
    }

    @Override
    public Order getOrder(int id) {
        Optional<Order> optionalOrder = mOrderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            return optionalOrder.get();
        } else {
            throw new OrderException(ResultEnum.ORDER_NOT_PRESENT);
        }
    }

    @Override
    public List<Order> getOrders() {
        return mOrderRepository.findAll();
    }

    @Override
    public PageItem<Order> getOrdersByPageNoCriteria(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        Page<Order> orderPage = mOrderRepository.findAll(pageable);
        return new PageItem<>((int) orderPage.getTotalElements(), orderPage.getContent());
    }

    @Override
    public PageItem<Order> getOrdersByName(int page, int size, String name) {
        return PageItem.createPageItem(
                mOrderRepository.findAllByName(name),
                page,
                size);
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
        if (soldDate != null && !"".equals(soldDate)) {
            try {
                order.setSoldDate(Date.valueOf(soldDate));
            } catch (Exception e) {
                throw new OrderException(ResultEnum.SOLD_DATE_FORMAT_INVALID);
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

            Order oldOrder = optionalOrder.get();
            newOrder.setId(oldOrder.getId());
            newOrder.setCreatedTime(oldOrder.getCreatedTime());

            if (name != null) {
                newOrder.setName(name);
            }
            if (phoneNumber != null) {
                newOrder.setPhoneNumber(phoneNumber);
            }
            if (address != null) {
                newOrder.setAddress(address);
            }
            if (soldDate != null && !"".equals(soldDate)) {
                try {
                    newOrder.setSoldDate(Date.valueOf(soldDate));
                } catch (Exception e) {
                    throw new OrderException(ResultEnum.SOLD_DATE_FORMAT_INVALID);
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

    @Transactional
    @Override
    public Order deleteOrder(int id) {
        Optional<Order> optionalOrder = mOrderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            mOrderRepository.delete(order);

            Optional<RecentDate> optionalRecentDate = mRecentDateRepository.findById(id);
            if (optionalRecentDate.isPresent()) {
                mRecentDateRepository.deleteById(id);
            }

            try {
                // 该删除操作需要数据库支持事务，否则报错javax.persistence.TransactionRequiredException
                mPostSoldConditionRepository.deleteByOrderId(id);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }

            return order;
        } else {
            throw new OrderException(ResultEnum.ORDER_NOT_PRESENT);
        }
    }
}
