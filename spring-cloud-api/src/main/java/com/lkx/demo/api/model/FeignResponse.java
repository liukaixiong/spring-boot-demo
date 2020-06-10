package com.lkx.demo.api.model;

import java.util.List;

/**
 * feign调用返回通用参数
 *
 * @author ： liukx
 * @time ： 2019/4/24 - 17:03
 */
public class FeignResponse<T> {

    private boolean success;
    private String message;
    private T obj;
    private List<T> list;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }


}
