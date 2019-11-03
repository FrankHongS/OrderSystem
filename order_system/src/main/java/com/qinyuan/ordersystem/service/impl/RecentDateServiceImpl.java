package com.qinyuan.ordersystem.service.impl;

import com.qinyuan.ordersystem.dao.RecentDateRepository;
import com.qinyuan.ordersystem.entity.RecentDate;
import com.qinyuan.ordersystem.service.RecentDateService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class RecentDateServiceImpl implements RecentDateService {

    private final RecentDateRepository mRecentDateRepository;

    public RecentDateServiceImpl(RecentDateRepository recentDateRepository) {
        this.mRecentDateRepository = recentDateRepository;
    }

    @Override
    public Optional<RecentDate> findByOrderId(int orderId) {
        return mRecentDateRepository.findById(orderId);
    }

    @Override
    public RecentDate addRecentDate(int orderId, Date date) {
        RecentDate recentDate=new RecentDate();
        recentDate.setOrderId(orderId);
        recentDate.setRecentDate(date);
        return mRecentDateRepository.save(recentDate);
    }

    @Override
    public RecentDate updateRecentDate(RecentDate newRecentDate) {
        return mRecentDateRepository.save(newRecentDate);
    }
}
