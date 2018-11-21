package com.nudt.query.entity;

import com.nudt.query.util.queryUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by haibozhang on 2018/11/3.
 */
public class OfflineLog extends Entity{

    //用户账号
//    private String userId ;
    //业务代码
    private String pId;
    //下线原因
    private String termCase;
    //用户在线时长
    private String sessinoTime;
    //下线时间
//    private String stopTime;

    public OfflineLog(){super();}

    public OfflineLog(String userId, String pId, String termCase, String sessinoTime, String stopTime) {
        super(userId,stopTime);
        this.pId = pId;
        this.termCase = termCase;
        this.sessinoTime = sessinoTime;
    }


    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getTermCase() {
        return termCase;
    }

    public void setTermCase(String termCase) {
        this.termCase = termCase;
    }

    public String getSessinoTime() {
        return sessinoTime;
    }

    public void setSessinoTime(String sessinoTime) {
        this.sessinoTime = sessinoTime;
    }

    @Override
    public String toString() {
        try {
            String stopTime = queryUtils.common_sdf.format(queryUtils.offline_sdf.parse(super.getTime()));
            return "OffLineLog{" +
                    "userId='" + super.getUserId() + '\'' +
                    ", pId='" + pId + '\'' +
                    ", termCase='" + termCase + '\'' +
                    ", sessinoTime='" + sessinoTime + '\'' +
                    ", stopTime='" + stopTime + '\'' +
                    '}';
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

}

