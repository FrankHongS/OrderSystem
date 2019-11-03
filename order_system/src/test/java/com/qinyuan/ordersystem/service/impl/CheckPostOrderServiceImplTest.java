package com.qinyuan.ordersystem.service.impl;

import com.qinyuan.ordersystem.dao.RecentDateRepository;
import com.qinyuan.ordersystem.entity.RecentDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CheckPostOrderServiceImplTest {

    @Autowired
    private RecentDateRepository recentDateRepository;

    @Test
    public void checkPostOrder() {

    }

    @Test
    public void getRecentDate() {
        Optional<RecentDate> optional = recentDateRepository.findById(12);

        assertTrue(optional.isPresent());
    }

    @Test
    public void addRecentDate() {
        RecentDate recentDate = new RecentDate();
        recentDate.setOrderId(12);
        recentDate.setRecentDate(Date.valueOf("2019-12-01"));

        RecentDate result = recentDateRepository.save(recentDate);
        System.out.println(result);
    }
}