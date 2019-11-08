package com.qinyuan.ordersystem.dao;

import com.qinyuan.ordersystem.entity.PostSoldCondition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostSoldConditionRepository extends JpaRepository<PostSoldCondition,Integer> {

    List<PostSoldCondition> findAllByOrderId(int orderId);

    List<PostSoldCondition> findAllByStatus(int status);

    void deleteByOrderId(int orderId);
}
