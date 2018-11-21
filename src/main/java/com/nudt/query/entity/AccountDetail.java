package com.nudt.query.entity;

/**
 * Created by Administrator on 2018/11/14.
 */
// 每位宽视账号的上下线详细信息，包括:IP地址，网卡地址，上线时间，上线业务，下线时间，在线时长，下线原因
public class AccountDetail {

    // 账号IP地址
    private String userIpAddr;
    // 网卡地址
    private String userNetAddr;
    // 上线时间
    private String userLoginTime;
    // 上线业务
    private String pID;
    // 下线时间
    private String stopTime;
    // 在线时长
    private String sessionTime;
    // 下线原因
    private String termCase;

    public AccountDetail(){}

    public AccountDetail(String userIpAddr, String userNetAddr, String userLoginTime,
                         String pID, String stopTime, String sessionTime, String termCase) {
        this.userIpAddr = userIpAddr;
        this.userNetAddr = userNetAddr;
        this.userLoginTime = userLoginTime;
        this.pID = pID;
        this.stopTime = stopTime;
        this.sessionTime = sessionTime;
        this.termCase = termCase;
    }

    public String getUserIpAddr() {
        return userIpAddr;
    }

    public void setUserIpAddr(String userIpAddr) {
        this.userIpAddr = userIpAddr;
    }

    public String getUserNetAddr() {
        return userNetAddr;
    }

    public void setUserNetAddr(String userNetAddr) {
        this.userNetAddr = userNetAddr;
    }

    public String getUserLoginTime() {
        return userLoginTime;
    }

    public void setUserLoginTime(String userLoginTime) {
        this.userLoginTime = userLoginTime;
    }

    public String getpID() {
        return pID;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionTime = sessionTime;
    }

    public String getTermCase() {
        return termCase;
    }

    public void setTermCase(String termCase) {
        this.termCase = termCase;
    }

    @Override
    public String toString() {
        return "AccountEntiy{" +
                "userIpAddr='" + userIpAddr + '\'' +
                ", userNetAddr='" + userNetAddr + '\'' +
                ", userLoginTime='" + userLoginTime + '\'' +
                ", pID='" + pID + '\'' +
                ", stopTime='" + stopTime + '\'' +
                ", sessionTime='" + sessionTime + '\'' +
                ", termCase='" + termCase + '\'' +
                '}';
    }

}
