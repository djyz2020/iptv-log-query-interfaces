package com.nudt.query.entity;

import java.util.Date;

/**
 * Created by Administrator on 2018/11/14.
 */
// elasticsearch区间查询实体
public class RangeEntity {
    private String rangeQueryName;  // 区间查询字段
    private String from;            // 区间左边界
    private boolean equalFrom;      // 是否包含左边界
    private String to;              // 区间右边界
    private boolean equalTo;         // 是否包含右边界

    public RangeEntity() {
        this.equalFrom = true;
        this.equalTo = true;
    }

    public RangeEntity(String rangeQueryName, String from, String to) {
        this.rangeQueryName = rangeQueryName;
        this.from = from;
        this.to = to;
        this.equalFrom = true;
        this.equalTo = true;
    }

    public RangeEntity(String rangeQueryName, String from, boolean equalFrom, String to, boolean equalTo) {
        this.rangeQueryName = rangeQueryName;
        this.from = from;
        this.equalFrom = equalFrom;
        this.to = to;
        this.equalTo = equalTo;
    }

    public String getRangeQueryName() {
        return rangeQueryName;
    }

    public void setRangeQueryName(String rangeQueryName) {
        this.rangeQueryName = rangeQueryName;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public boolean isEqualFrom() {
        return equalFrom;
    }

    public void setEqualFrom(boolean equalFrom) {
        this.equalFrom = equalFrom;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public boolean isEqualTo() {
        return equalTo;
    }

    public void setEqualTo(boolean equalTo) {
        this.equalTo = equalTo;
    }

    @Override
    public String toString() {
        return "RangeEntity{" +
                "rangeQueryName='" + rangeQueryName + '\'' +
                ", from='" + from + '\'' +
                ", equalFrom=" + equalFrom +
                ", to='" + to + '\'' +
                ", equalTo='" + equalTo + '\'' +
                '}';
    }

}
