package com.qinyuan.ordersystem.controller;

import com.qinyuan.ordersystem.entity.Order;
import com.qinyuan.ordersystem.entity.PostSoldCondition;
import com.qinyuan.ordersystem.service.PostSoldService;
import com.qinyuan.ordersystem.util.ResultUtil;
import com.qinyuan.ordersystem.vo.PageItem;
import com.qinyuan.ordersystem.vo.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postsold")
public class PostSoldController {

    private final PostSoldService mPostSoldService;

    public PostSoldController(PostSoldService postSoldService) {
        this.mPostSoldService = postSoldService;
    }

    @GetMapping("/query/page")
    public Result<PageItem<Order>> getTodoOrders(@RequestParam int page,
                                                 @RequestParam int size) {
        return ResultUtil.success(mPostSoldService.getAllTodoOrders(page, size));
    }

    @GetMapping("/query/page/name")
    public Result<PageItem<Order>> getTodoOrdersByName(@RequestParam int page,
                                                       @RequestParam int size,
                                                       @RequestParam String name) {
        return ResultUtil.success(mPostSoldService.getAllTodoOrdersByName(page, size, name));
    }

    @GetMapping("/query")
    public Result<List<PostSoldCondition>> getPostSoldInfo(@RequestParam int orderId) {
        return ResultUtil.success(mPostSoldService.getPostSoldConditions(orderId));
    }

    @PostMapping("/update")
    public Result<PostSoldCondition> updatePostSold(@RequestParam int id,
                                                    @RequestParam int status,
                                                    String comment) {
        return ResultUtil.success(mPostSoldService.updatePostSold(id, status, comment));
    }

}
