package com.nudt.query.entity;

import com.nudt.query.util.queryUtils;
import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

/**
 * 报告实体
 * Created by Administrator on 2018/11/16.
 */
@Entity
@Table(name = "reportEntity")
public class ReportEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reportId")
    // 报告ID
    private long reportId;

    @Column(name = "reportDate")
    // 报告日期
    private String reportDate;

    @Lob
//    @Basic(fetch = FetchType.LAZY) // 普通属性不需要延迟处理
    @Column(name = "reportData", columnDefinition="TEXT")
    // 报告数据
    private String reportData;

    public ReportEntity() {}

    public ReportEntity(long reportId, Date reportDate, String reportData) {
        this.reportId = reportId;
        this.reportDate = queryUtils.common_sdf.format(reportDate);
        this.reportData = reportData;
    }

    public ReportEntity(Date reportDate, String reportData) {
        this.reportDate = queryUtils.common_sdf.format(reportDate);
        this.reportData = reportData;
    }

    public long getReportId() {
        return reportId;
    }

    public void setReportId(long reportId) {
        this.reportId = reportId;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = queryUtils.common_sdf.format(reportDate);
    }

    public String getReportData() {
        return reportData;
    }

    public void setReportData(String reportData) {
        this.reportData = reportData;
    }

    @Override
    public String toString() {
        return "ReportEntity{" +
                "reportId=" + reportId +
                ", reportDate='" + reportDate + '\'' +
                ", reportData='" + reportData + '\'' +
                '}';
    }

}
