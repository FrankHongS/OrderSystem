package com.qinyuan.ordersystem.vo;

public enum ResultEnum {
    SUCCESS(0,"success"),
    UNKNOWN(-1,"unknown error"),
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
