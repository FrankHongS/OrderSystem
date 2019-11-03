package com.qinyuan.ordersystem.service;

import com.qinyuan.ordersystem.entity.PostSoldCondition;

import java.sql.Date;
import java.util.List;

public interface PostSoldService {

    List<PostSoldCondition> getPostSoldConditions(int orderId);

    PostSoldCondition addPostSold(
            int orderId,
            Date todoDate,
            int status
    );

    PostSoldCondition updatePostSold(
            int id,
            int status
    );

    PostSoldCondition deletePostSold(
            int id
    );
}
