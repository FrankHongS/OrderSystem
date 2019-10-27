package com.qinyuan.ordersystem.exception;

import com.qinyuan.ordersystem.vo.ResultEnum;

public class OrderException extends RuntimeException {
    private ResultEnum resultEnum;

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.resultEnum = resultEnum;
    }

    public ResultEnum getResultEnum() {
        return resultEnum;
    }
}
