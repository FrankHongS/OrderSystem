package com.qinyuan.ordersystem.vo;

public enum ResultEnum {
    SUCCESS(0,"success"),
    UNKNOWN(-1,"unknown error"),

    DATE_FORMAT_INVALID(1,"sold date is invalid"),

    ORDER_NOT_PRESENT(10,"order not present"),
    ;

    private int code;

    private String message;

    ResultEnum(int code,String message){
        this.code=code;
        this.message=message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
