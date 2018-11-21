package com.nudt.query.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/11/14.
 */
public class AccountEntity {

    // 账号ID
    private String userId;
    // 账号上下线详情
    private List<Entity> list;

    public AccountEntity() {}

    public AccountEntity(String userId, List<Entity> list) {
        this.userId = userId;
        this.list = list;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Entity> getList() {
        return list;
    }

    public void setList(List<Entity> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "userId='" + userId + '\'' +
                ", list=" + list +
                '}';
    }

}
