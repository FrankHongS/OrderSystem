package com.qinyuan.ordersystem.controller;

import com.qinyuan.ordersystem.entity.Order;
import com.qinyuan.ordersystem.service.OrderService;
import com.qinyuan.ordersystem.util.ResultUtil;
import com.qinyuan.ordersystem.vo.PageItem;
import com.qinyuan.ordersystem.vo.Result;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService mOrderService;

    public OrderController(OrderService orderService) {
        this.mOrderService = orderService;
    }

    @GetMapping("/query/one")
    public Result<Order> getOrder(@RequestParam int id) {
        return ResultUtil.success(mOrderService.getOrder(id));
    }

    @GetMapping("/query")
    public Result<List<Order>> getOrders() {
        return ResultUtil.success(mOrderService.getOrders());
    }

    @GetMapping("/query/page")
    public Result<PageItem<Order>> getOrdersByPage(@RequestParam int page,
                                                   @RequestParam int size) {
        return ResultUtil.success(mOrderService.getOrdersByPageNoCriteria(page, size));
    }

    @GetMapping("/query/page/name")
    public Result<PageItem<Order>> getOrdersByName(@RequestParam int page,
                                                   @RequestParam int size,
                                                   @RequestParam String name) {
        return ResultUtil.success(mOrderService.getOrdersByName(page, size, name));
    }

    @PostMapping("/save")
    public Result<Order> saveOrder(@RequestParam String name,
                                   String phoneNumber,
                                   String address,
                                   String soldDate,
                                   String machineType) {
        Order savedOrder = mOrderService.addOrder(name, phoneNumber, address, soldDate, machineType);
        return ResultUtil.success(savedOrder);
    }

    @PostMapping("/update")
    public Result<Order> updateOrder(@RequestParam int id,
                                     String name,
                                     String phoneNumber,
                                     String address,
                                     String soldDate,
                                     String machineType) {
        Order updatedOrder = mOrderService.updateOrder(id, name, phoneNumber, address, soldDate, machineType);
        return ResultUtil.success(updatedOrder);
    }

    @DeleteMapping("/delete")
    public Result<Order> deleteOrder(@RequestParam int id) {
        Order deletedOrder = mOrderService.deleteOrder(id);
        return ResultUtil.success(deletedOrder);
    }

}
