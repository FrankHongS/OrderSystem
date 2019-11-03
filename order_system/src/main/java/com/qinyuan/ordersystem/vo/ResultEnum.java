package com.qinyuan.ordersystem.vo;

public enum ResultEnum {
    SUCCESS(0,"success"),
    UNKNOWN(-1,"unknown error"),

    SOLD_DATE_FORMAT_INVALID(1,"sold date is invalid"),
    TODO_DATE_FORMAT_INVALID(2,"todo date is invalid"),

    ORDER_NOT_PRESENT(10,"order not present"),
    POST_SOLD_NOT_PRESENT(11,"post sold not present"),

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
