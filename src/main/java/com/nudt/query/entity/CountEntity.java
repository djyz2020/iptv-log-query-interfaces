package com.nudt.query.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

/**
 * Created by haibozhang on 2018/11/9.
 */
// 统计实体
@ApiModel(value = "统计对象")
public class CountEntity {

    // OLT设备IP
    @ApiModelProperty(value = "OLT设备IP")
    private String oltDevIp;
    // OLT设备下行端口
    @ApiModelProperty(value = "OLT设备下行端口号")
    private String oltDevDport;
    // 统计时间
    @ApiModelProperty(value = "统计时间")
    private String countTime;
    // 在线宽带用户数
    @ApiModelProperty(value = "在线宽带用户数")
    private long onlineCountBD;
    // 在线IPTV用户数
    @ApiModelProperty(value = "在线IPTV用户数")
    private long onlineCountIPTV;
    // 注册宽带用户数
    @ApiModelProperty(value = "注册宽带用户数")
    private long userCountBD;
    // 注册IPTV用户数
    @ApiModelProperty(value = "注册IPTV用户数")
    private long userCountIPTV;
    // 在线宽带用户上下线信息列表
    @ApiModelProperty(value = "在线宽带用户上下线信息列表")
    private List<AccountEntity> onlineBDList;
    // 在线IPTV用户上下线信息列表
    @ApiModelProperty(value = "在线IPTV用户上下线信息列表")
    private List<AccountEntity> onlineIPTVList;

    public CountEntity() {}

    public CountEntity(String oltDevIp, String oltDevDport, String countTime,
                       long onlineCountBD, long onlineCountIPTV, long userCountBD,
                       long userCountIPTV, List<AccountEntity> onlineBDList,
                       List<AccountEntity> onlineIPTVList) {
        this.oltDevIp = oltDevIp;
        this.oltDevDport = oltDevDport;
        this.countTime = countTime;
        this.onlineCountBD = onlineCountBD;
        this.onlineCountIPTV = onlineCountIPTV;
        this.userCountBD = userCountBD;
        this.userCountIPTV = userCountIPTV;
        this.onlineBDList = onlineBDList;
        this.onlineIPTVList = onlineIPTVList;
    }

    public String getOltDevIp() {
        return oltDevIp;
    }

    public void setOltDevIp(String oltDevIp) {
        this.oltDevIp = oltDevIp;
    }

    public String getOltDevDport() {
        return oltDevDport;
    }

    public void setOltDevDport(String oltDevDport) {
        this.oltDevDport = oltDevDport;
    }

    public String getCountTime() {
        return countTime;
    }

    public void setCountTime(String countTime) {
        this.countTime = countTime;
    }

    public long getOnlineCountBD() {
        return onlineCountBD;
    }

    public void setOnlineCountBD(long onlineCountBD) {
        this.onlineCountBD = onlineCountBD;
    }

    public long getOnlineCountIPTV() {
        return onlineCountIPTV;
    }

    public void setOnlineCountIPTV(long onlineCountIPTV) {
        this.onlineCountIPTV = onlineCountIPTV;
    }

    public long getUserCountBD() {
        return userCountBD;
    }

    public void setUserCountBD(long userCountBD) {
        this.userCountBD = userCountBD;
    }

    public long getUserCountIPTV() {
        return userCountIPTV;
    }

    public void setUserCountIPTV(long userCountIPTV) {
        this.userCountIPTV = userCountIPTV;
    }

    public List<AccountEntity> getOnlineBDList() {
        return onlineBDList;
    }

    public void setOnlineBDList(List<AccountEntity> onlineBDList) {
        this.onlineBDList = onlineBDList;
    }

    public List<AccountEntity> getOnlineIPTVList() {
        return onlineIPTVList;
    }

    public void setOnlineIPTVList(List<AccountEntity> onlineIPTVList) {
        this.onlineIPTVList = onlineIPTVList;
    }

    @Override
    public String toString() {
        return "CountEntity{" +
                "oltDevIp='" + oltDevIp + '\'' +
                ", oltDevDport='" + oltDevDport + '\'' +
                ", countTime='" + countTime + '\'' +
                ", onlineCountBD=" + onlineCountBD +
                ", onlineCountIPTV=" + onlineCountIPTV +
                ", userCountBD=" + userCountBD +
                ", userCountIPTV=" + userCountIPTV +
                ", onlineBDList=" + onlineBDList +
                ", onlineIPTVList=" + onlineIPTVList +
                '}';
    }

}
