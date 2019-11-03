package com.qinyuan.ordersystem.service;

import com.qinyuan.ordersystem.entity.RecentDate;

import java.sql.Date;
import java.util.Optional;

public interface RecentDateService {

    Optional<RecentDate> findByOrderId(int orderId);

    RecentDate addRecentDate(int orderId, Date recentDate);

    RecentDate updateRecentDate(RecentDate newRecentDate);

}
