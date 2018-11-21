package com.nudt.query.entity;

/**
 * Created by Administrator on 2018/11/16.
 */
public class MonitorRecord {
    private String date;
    private double hardDisk;
    private double memory;
    private double cpu;
    private int instance;

    public MonitorRecord(){}

    public MonitorRecord(String date, double hardDisk, double memory, double cpu, int instance) {
        this.date = date;
        this.hardDisk = hardDisk;
        this.memory = memory;
        this.cpu = cpu;
        this.instance = instance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(double hardDisk) {
        this.hardDisk = hardDisk;
    }

    public double getMemory() {
        return memory;
    }

    public void setMemory(double memory) {
        this.memory = memory;
    }

    public double getCpu() {
        return cpu;
    }

    public void setCpu(double cpu) {
        this.cpu = cpu;
    }

    public int getInstance() {
        return instance;
    }

    public void setInstance(int instance) {
        this.instance = instance;
    }

    @Override
    public String toString() {
        return "MonitorRecord{" +
                "date='" + date + '\'' +
                ", hardDisk='" + hardDisk + '\'' +
                ", memory='" + memory + '\'' +
                ", cpu='" + cpu + '\'' +
                ", instance='" + instance + '\'' +
                '}';
    }

}
