package com.qinyuan.ordersystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class RecentDate {
    @Id
    private int orderId;

    @Column(nullable = false)
    private Date recentDate;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getRecentDate() {
        return recentDate;
    }

    public void setRecentDate(Date recentDate) {
        this.recentDate = recentDate;
    }

    @Override
    public String toString() {
        return "RecentDate{" +
                "orderId=" + orderId +
                ", recentDate=" + recentDate +
                '}';
    }
}
