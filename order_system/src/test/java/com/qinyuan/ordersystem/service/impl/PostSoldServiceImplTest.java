package com.qinyuan.ordersystem.service.impl;

import com.qinyuan.ordersystem.entity.PostSoldCondition;
import com.qinyuan.ordersystem.service.PostSoldService;
import com.qinyuan.ordersystem.util.PostSoldConditionStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostSoldServiceImplTest {

    @Autowired
    private PostSoldService postSoldService;

    @Test
    public void getPostSoldConditions() {
        List<PostSoldCondition> conditionList=postSoldService.getPostSoldConditions(12);

        System.out.println(conditionList);
    }

    @Test
    public void addPostSold() {
        PostSoldCondition condition = postSoldService.addPostSold(
                12, Date.valueOf("2017-12-01"), PostSoldConditionStatus.TODO
        );

        System.out.println(condition);
//        PostSoldCondition{id=68, orderId=12, todoDate=2017-12-01, status=1, createdTime=2019-11-03, lastUpdatedTime=2019-11-03}
    }

    @Test
    public void updatePostSold() {
        PostSoldCondition condition = postSoldService.updatePostSold(
                68, PostSoldConditionStatus.DONE
        );

        System.out.println(condition);
//        PostSoldCondition{id=68, orderId=12, todoDate=2017-12-01, status=0, createdTime=2019-11-03, lastUpdatedTime=2019-11-03}
    }

    @Test
    public void deletePostSold() {
        PostSoldCondition condition = postSoldService.deletePostSold(68);

        System.out.println(condition);
    }
}
