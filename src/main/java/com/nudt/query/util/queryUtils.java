package com.nudt.query.util;

import com.nudt.query.Const.IndexType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by haibozhang on 2018/11/9.
 */
public class queryUtils {
    private static Logger logger = LoggerFactory.getLogger(queryUtils.class);

    public static SimpleDateFormat common_sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public static SimpleDateFormat online_sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public static SimpleDateFormat offline_sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 日期格式化
     * @param date  日期
     * @param type  类型
     * @return
     */
    public static Date dateFormatToDate(Date date, IndexType type){
        if(StringUtils.isEmpty(date)){
            logger.warn("日期参数为空!");
            return null;
        }else{
            try{
                switch (type){
                    case ONLINE:
                        return online_sdf.parse(common_sdf.format(date));
                    case OFFLINE:
                        return offline_sdf.parse(common_sdf.format(date));
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 日期格式化
     * @param date  日期
     * @param type  类型
     * @return
     */
    public static String dateFormatToString(Date date, IndexType type){
        if(StringUtils.isEmpty(date)){
            logger.warn("日期参数为空!");
            return null;
        }else{
            try{
                switch (type){
                    case ONLINE:
                        return online_sdf.format(date);
                    case OFFLINE:
                        return offline_sdf.format(date);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 日期格式化
     * @param date  日期
     * @param type  类型
     * @return
     */
    public static Date dateFormatToDate(String date, IndexType type){
        if(StringUtils.isEmpty(date)){
            logger.warn("日期参数为空!");
            return null;
        }else{
            try{
                switch (type){
                    case ONLINE:
                        return online_sdf.parse(date);
                    case OFFLINE:
                        return offline_sdf.parse(date);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 计算几天前的时间
     * @param date
     * @param day
     * @return
     */
    public static Date getDateBefore(Date date,int day){
        Calendar now =Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
        return now.getTime();
    }

    /**
     * 计算几天后的时间
     * @param date
     * @param day
     * @return
     */
    public static Date getDateAfter(Date date,int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

}
