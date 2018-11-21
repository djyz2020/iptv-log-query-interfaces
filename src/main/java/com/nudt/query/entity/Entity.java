package com.nudt.query.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/11/14.
 */
public class Entity  implements Serializable,Comparable<Entity> {

    private static final long serialVersionUID = -763638353551774167L;

    private String userId;
    private String time;

    public Entity(){};

    public Entity(String userId, String time) {
        this.userId = userId;
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int compareTo(Entity o){
        try {
            Date this_time = new Date();
            Date o_time = new Date();
            SimpleDateFormat off_format = new SimpleDateFormat("yyyyMMddHHmmss");
            SimpleDateFormat on_format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            if(this instanceof OfflineLog) {
                this_time = off_format.parse(this.getTime().trim());
            }else if (this instanceof OnlineLog){
                this_time = on_format.parse(this.getTime().trim());
            }
            if(o instanceof OfflineLog){
                o_time = off_format.parse(o.getTime().trim());
            }else if(o instanceof OnlineLog){
                o_time = on_format.parse(o.getTime().trim());
            }
            return this_time.compareTo(o_time);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}