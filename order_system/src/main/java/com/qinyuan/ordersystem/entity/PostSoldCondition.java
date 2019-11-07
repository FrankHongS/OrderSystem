package com.qinyuan.ordersystem.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class PostSoldCondition {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private int orderId;

    @Column(nullable = false)
    private Date todoDate;

    private String comment;

    @Column(nullable = false)
    private int status;

    @CreationTimestamp
    private Date createdTime;

    @UpdateTimestamp
    private Date lastUpdatedTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getTodoDate() {
        return todoDate;
    }

    public void setTodoDate(Date todoDate) {
        this.todoDate = todoDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    @Override
    public String toString() {
        return "PostSoldCondition{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", todoDate=" + todoDate +
                ", status=" + status +
                ", createdTime=" + createdTime +
                ", lastUpdatedTime=" + lastUpdatedTime +
                '}';
    }
}
