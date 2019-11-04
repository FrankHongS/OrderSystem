package com.qinyuan.ordersystem.service.impl;

import com.qinyuan.ordersystem.dao.OrderRepository;
import com.qinyuan.ordersystem.dao.PostSoldConditionRepository;
import com.qinyuan.ordersystem.entity.Order;
import com.qinyuan.ordersystem.entity.PostSoldCondition;
import com.qinyuan.ordersystem.exception.OrderException;
import com.qinyuan.ordersystem.service.PostSoldService;
import com.qinyuan.ordersystem.util.PostSoldConditionStatus;
import com.qinyuan.ordersystem.vo.PageItem;
import com.qinyuan.ordersystem.vo.ResultEnum;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
public class PostSoldServiceImpl implements PostSoldService {

    private final PostSoldConditionRepository mPostSoldRepository;
    private final OrderRepository mOrderRepository;

    public PostSoldServiceImpl(PostSoldConditionRepository postSoldRepository, OrderRepository orderRepository) {
        this.mPostSoldRepository = postSoldRepository;
        this.mOrderRepository = orderRepository;
    }

    @Override
    public PageItem<Order> getAllTodoOrders(int page, int size) {
        return PageItem.createPageItem(getTodoOrders(), page, size);
    }

    @Override
    public PageItem<Order> getAllTodoOrdersByName(int page, int size, String name) {
        List<Order> orderList = getTodoOrders();

        List<Order> newOrderList = new ArrayList<>();
        for (Order order : orderList) {
            if (order.getName().equals(name)) {
                newOrderList.add(order);
            }
        }

        return PageItem.createPageItem(newOrderList, page, size);
    }

    @Override
    public List<PostSoldCondition> getPostSoldConditions(int orderId) {
        return mPostSoldRepository.findAllByOrderId(orderId);
    }

    @Override
    public PostSoldCondition addPostSold(int orderId, Date todoDate, int status) {
        PostSoldCondition condition = new PostSoldCondition();

        condition.setOrderId(orderId);
        condition.setTodoDate(todoDate);
        condition.setStatus(status);

        return mPostSoldRepository.save(condition);
    }

    @Override
    public PostSoldCondition updatePostSold(int id, int status) {
        Optional<PostSoldCondition> conditionOptional = mPostSoldRepository.findById(id);
        if (conditionOptional.isPresent()) {
            PostSoldCondition oldCondition = conditionOptional.get();
            oldCondition.setStatus(status);
            return mPostSoldRepository.save(oldCondition);
        } else {
            throw new OrderException(ResultEnum.POST_SOLD_NOT_PRESENT);
        }
    }

    @Override
    public PostSoldCondition deletePostSold(int id) {
        Optional<PostSoldCondition> optionalOrder = mPostSoldRepository.findById(id);
        if (optionalOrder.isPresent()) {
            PostSoldCondition condition = optionalOrder.get();
            mPostSoldRepository.delete(condition);
            return condition;
        } else {
            throw new OrderException(ResultEnum.POST_SOLD_NOT_PRESENT);
        }
    }

    private List<Order> getTodoOrders() {
        List<PostSoldCondition> conditionList = mPostSoldRepository.findAllByStatus(PostSoldConditionStatus.TODO);
        List<Integer> orderIdList = new ArrayList<>();
        for (PostSoldCondition condition : conditionList) {
            int orderId = condition.getOrderId();
            if (!orderIdList.contains(orderId)) {
                orderIdList.add(orderId);
            }
        }
        List<Order> orderList = new ArrayList<>();
        for (int orderId : orderIdList) {
            Optional<Order> orderOptional = mOrderRepository.findById(orderId);
            if (orderOptional.isPresent()) {
                Order order = orderOptional.get();
                orderList.add(order);
            }
        }

        return orderList;
    }
}
