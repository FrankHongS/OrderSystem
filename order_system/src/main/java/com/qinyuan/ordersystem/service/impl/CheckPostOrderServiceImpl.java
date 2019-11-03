package com.qinyuan.ordersystem.service.impl;

import com.qinyuan.ordersystem.dao.RecentDateRepository;
import com.qinyuan.ordersystem.entity.Order;
import com.qinyuan.ordersystem.entity.RecentDate;
import com.qinyuan.ordersystem.service.CheckPostOrderService;
import com.qinyuan.ordersystem.service.OrderService;
import com.qinyuan.ordersystem.service.PostSoldService;
import com.qinyuan.ordersystem.service.RecentDateService;
import com.qinyuan.ordersystem.util.Constants;
import com.qinyuan.ordersystem.util.PostSoldConditionStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CheckPostOrderServiceImpl implements CheckPostOrderService {

    private final OrderService mOrderService;
    private final PostSoldService mPostSoldService;
    private final RecentDateService mRecentDateService;

    public CheckPostOrderServiceImpl(OrderService orderService, PostSoldService postSoldService,
                                     RecentDateService recentDateService) {
        this.mOrderService = orderService;
        this.mPostSoldService = postSoldService;
        this.mRecentDateService = recentDateService;
    }

    @Override
    public void checkPostOrder() {
        List<Order> orders = mOrderService.getOrders();

        for (Order order : orders) {
            int orderId = order.getId();
            Optional<RecentDate> optionalRecentDate = mRecentDateService.findByOrderId(orderId);

            Date preDate;
            RecentDate recentDate = null;
            if (optionalRecentDate.isPresent()) {
                recentDate = optionalRecentDate.get();
                preDate = recentDate.getRecentDate();
            } else {
                preDate = order.getSoldDate();
            }

            Date todoDate = new Date(preDate.getTime() + Constants.INTERVAL);
            long deltaTime = System.currentTimeMillis()
                    - todoDate.getTime() + Constants.TIME_BEFORE;

            if (deltaTime > 0) {
                // add post sold condition
                mPostSoldService.addPostSold(orderId, todoDate, PostSoldConditionStatus.TODO);
                if (recentDate != null) {
                    // update recent date
                    recentDate.setRecentDate(todoDate);
                    mRecentDateService.updateRecentDate(recentDate);
                } else {
                    mRecentDateService.addRecentDate(orderId, todoDate);
                }
            }
        }
    }

}
