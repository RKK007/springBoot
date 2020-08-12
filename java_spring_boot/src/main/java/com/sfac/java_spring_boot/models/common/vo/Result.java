package com.sfac.java_spring_boot.models.common.vo;

/*
@author:Lenovo
@program:java_spring_boot
@date:2020-08-12 09:58:08
@description:
*/
public class Result<T> {
    private final static int SUCCESS_CODE = 200;
    private final static int FAILD_CODE = 500;


    private int status;
    private String message;
    private T object;//任何对象

    public static int getSuccessCode() {
        return SUCCESS_CODE;
    }

    public static int getFaildCode() {
        return FAILD_CODE;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public Result() {
    }

    public Result(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public Result(int status, String message, T object) {
        this.status = status;
        this.message = message;
        this.object = object;
    }

    public enum ResultStatus{
        SUCCESS(200),FAILD(500);
        public int status;

        ResultStatus(int status) {
            this.status = status;
        }
    }


}
