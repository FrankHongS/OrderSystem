package com.qinyuan.ordersystem.controller;

import com.qinyuan.ordersystem.entity.Order;
import com.qinyuan.ordersystem.service.PostSoldService;
import com.qinyuan.ordersystem.util.ResultUtil;
import com.qinyuan.ordersystem.vo.PageItem;
import com.qinyuan.ordersystem.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
