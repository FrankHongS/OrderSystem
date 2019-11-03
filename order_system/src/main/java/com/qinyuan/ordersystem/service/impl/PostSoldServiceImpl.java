package com.qinyuan.ordersystem.service.impl;

import com.qinyuan.ordersystem.dao.PostSoldConditionRepository;
import com.qinyuan.ordersystem.entity.PostSoldCondition;
import com.qinyuan.ordersystem.exception.OrderException;
import com.qinyuan.ordersystem.service.PostSoldService;
import com.qinyuan.ordersystem.vo.ResultEnum;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostSoldServiceImpl implements PostSoldService {

    private final PostSoldConditionRepository mPostSoldRepository;

    public PostSoldServiceImpl(PostSoldConditionRepository postSoldRepository) {
        this.mPostSoldRepository = postSoldRepository;
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
}