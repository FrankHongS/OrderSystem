package com.qinyuan.ordersystem.controller;

import com.qinyuan.ordersystem.entity.Order;
import com.qinyuan.ordersystem.util.ResultUtil;
import com.qinyuan.ordersystem.vo.Result;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/query")
    public Result<List<Order>> getOrders(){
        List<Order> orders=new ArrayList<>();
        return ResultUtil.success(orders);
    }

    @PostMapping("/save")
    public Result<Order> saveOrder(Order order){
        return ResultUtil.success(order);
    }

    @PostMapping("/update")
    public Result<Order> updateOrder(Order order){
        return ResultUtil.success(order);
    }

    @DeleteMapping("/delete")
    public Result<Order> deleteOrder(@RequestParam int id){
        Order order=new Order(id,null,null,null,null,null);
        return ResultUtil.success(order);
    }

}
