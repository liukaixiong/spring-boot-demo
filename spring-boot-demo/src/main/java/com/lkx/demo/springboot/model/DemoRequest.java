package com.lkx.demo.springboot.model;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 请求案例
 *
 * @author ： liukx
 * @time ： 2019/6/3 - 13:47
 */
public class DemoRequest {

    private String username;
    @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date created;

    private JSONObject jsonObject;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Override
    public String toString() {
        return "DemoRequest{" +
                "username='" + username + '\'' +
                ", created=" + created +
                ", jsonObject=" + jsonObject +
                '}';
    }
}
