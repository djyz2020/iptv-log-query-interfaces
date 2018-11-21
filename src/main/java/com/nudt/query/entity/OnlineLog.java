package com.nudt.query.entity;

/**
 * Created by haibozhang on 2018/10/24.
 */
public class OnlineLog extends Entity{

//    private String userId;            // 宽带账号：电信用户账号，IIV账号：在账号后加@vod，以便还原成IIV账号
    private String idType;              // 账号类型，空，宽带账号；vod，IIV账号
    private String pId;                 // 业务代码，识别账号办理的业务种类，2开头，代表宽视账号；10结尾代表IPTV账号；别称BUSI_TYPE
    private String userIpAddr;          // 电信用户拨号所获得的IP地址
    private String brasIpAddr;          // 宽带接入服务器IP地址
    private String brasLogicPort;       // 宽带接入服务器逻辑接口
    private String userNetAddr;         // 电信用户上网网卡地址
//    private String userLoginTime;     // 上线时间

    public OnlineLog(){}

    public OnlineLog(String userId, String idType, String pId, String userIpAddr, String brasIpAddr,
                     String brasLogicPort, String userNetAddr, String userLoginTime) {
        super(userId, userLoginTime);
        this.idType = idType;
        this.pId = pId;
        this.userIpAddr = userIpAddr;
        this.brasIpAddr = brasIpAddr;
        this.brasLogicPort = brasLogicPort;
        this.userNetAddr = userNetAddr;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getUserIpAddr() {
        return userIpAddr;
    }

    public void setUserIpAddr(String userIpAddr) {
        this.userIpAddr = userIpAddr;
    }

    public String getBrasIpAddr() {
        return brasIpAddr;
    }

    public void setBrasIpAddr(String brasIpAddr) {
        this.brasIpAddr = brasIpAddr;
    }

    public String getBrasLogicPort() {
        return brasLogicPort;
    }

    public void setBrasLogicPort(String brasLogicPort) {
        this.brasLogicPort = brasLogicPort;
    }

    public String getUserNetAddr() {
        return userNetAddr;
    }

    public void setUserNetAddr(String userNetAddr) {
        this.userNetAddr = userNetAddr;
    }

    @Override
    public String toString() {
        return "OnlineLog{" +
                "userId='" + super.getUserId() + '\'' +
                ", idType='" + idType + '\'' +
                ", pId='" + pId + '\'' +
                ", userIpAddr='" + userIpAddr + '\'' +
                ", brasIpAddr='" + brasIpAddr + '\'' +
                ", brasLogicPort='" + brasLogicPort + '\'' +
                ", userNetAddr='" + userNetAddr + '\'' +
                ", userLoginTime='" + super.getTime() + '\'' +
                '}';
    }

}
