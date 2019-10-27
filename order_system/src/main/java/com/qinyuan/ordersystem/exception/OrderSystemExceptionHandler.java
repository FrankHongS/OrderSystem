package com.qinyuan.ordersystem.exception;

import com.qinyuan.ordersystem.util.ResultUtil;
import com.qinyuan.ordersystem.vo.Result;
import com.qinyuan.ordersystem.vo.ResultEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Component
public class OrderSystemExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result handle(Exception e){

        if (e instanceof OrderException){
            OrderException orderException= (OrderException) e;
            return ResultUtil.error(orderException.getResultEnum());
        }else {
            return ResultUtil.error(ResultEnum.UNKNOWN);
        }

    }
}
