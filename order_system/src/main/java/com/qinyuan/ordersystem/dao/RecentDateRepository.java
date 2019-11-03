package com.qinyuan.ordersystem.dao;

import com.qinyuan.ordersystem.entity.RecentDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecentDateRepository extends JpaRepository<RecentDate,Integer> {
}
