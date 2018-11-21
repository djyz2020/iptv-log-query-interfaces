package com.nudt.query.entity;

/**
 * Created by haibozhang on 2018/11/3.
 */
public class TopoLog {
    private String custId;                  //1.电信用户ID
    private String suerName;                //2.用户姓名
    private String braNo;                   //3.宽带拨入号
    private String areaId;                  //4.区域本地网编号/地区代码
    private String contract;                //5.电信用户联系电话
    private String userAddr;                //6.用户地址
    private String userLevel;               //7.用户等级
    private String userOpenTime;            //8.用户开通服务的时间
    private String userBdAcct;              //9.电信用户的宽带账号（Broadband Remote Access Server 宽带远程服务器）
    private String userItvAcct;             //10.电信用户的IIV账号 （IPTV Internet Protocol Television 互联协议电视）
    private String brasDevIp;               //11.BRAS的IP地址
    private String brasDevDport;            //12.经由BRAS的逻辑端口ID
    private String swDevIp;                 //13.汇聚层设备的IP地址
    private String swDevUport;              //14.汇聚层设备的上行端口
    private String swDevDport;              //15.汇聚层设备的下行端口
    private String oltDevIp;                //16.OLT设备的IP地址
    private String oltDevUport;             //17.OLT设备的上行端口
    private String oltDevDport;             //18.OLT设备的下行端口
    private String svlan;                   //19.服务虚拟局域网的ID
    private String cvlan;                   //20.客户虚拟局域网的ID
    private String onuLoginId;              //21.光猫逻辑ID
    private String familyNgId;              //22.电信用户的家庭网关ID
    private String familyNgTypeName;        //23.电信用户的家庭网关型号
    private String userDevMac;              //24.电信用户设备的MAC
    private String obd2Name;                //25.OBD2的名称
    private String obd1Name;                //26.OBD1的名称
    private String bam;                     //27.宽带接入方式
    private String userServicePackage;      //28.电信用户的套餐类型

    public TopoLog() {}

    public TopoLog(String custId, String suerName, String braNo, String areaId, String contract,
                   String userAddr, String userLevel, String userOpenTime, String userBdAcct,
                   String userItvAcct, String brasDevIp, String brasDevDport, String swDevIp,
                   String swDevUport, String swDevDport, String oltDevIp, String oltDevUport,
                   String oltDevDport, String svlan, String cvlan, String onuLoginId,
                   String familyNgId, String familyNgTypeName, String userDevMac,
                   String obd2Name, String obd1Name, String bam, String userServicePackage) {
        this.custId = custId;
        this.suerName = suerName;
        this.braNo = braNo;
        this.areaId = areaId;
        this.contract = contract;
        this.userAddr = userAddr;
        this.userLevel = userLevel;
        this.userOpenTime = userOpenTime;
        this.userBdAcct = userBdAcct;
        this.userItvAcct = userItvAcct;
        this.brasDevIp = brasDevIp;
        this.brasDevDport = brasDevDport;
        this.swDevIp = swDevIp;
        this.swDevUport = swDevUport;
        this.swDevDport = swDevDport;
        this.oltDevIp = oltDevIp;
        this.oltDevUport = oltDevUport;
        this.oltDevDport = oltDevDport;
        this.svlan = svlan;
        this.cvlan = cvlan;
        this.onuLoginId = onuLoginId;
        this.familyNgId = familyNgId;
        this.familyNgTypeName = familyNgTypeName;
        this.userDevMac = userDevMac;
        this.obd2Name = obd2Name;
        this.obd1Name = obd1Name;
        this.bam = bam;
        this.userServicePackage = userServicePackage;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getSuerName() {
        return suerName;
    }

    public void setSuerName(String suserName) {
        this.suerName = suerName;
    }

    public String getBraNo() {
        return braNo;
    }

    public void setBraNo(String braNo) {
        this.braNo = braNo;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getUserAddr() {
        return userAddr;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserOpenTime() {
        return userOpenTime;
    }

    public void setUserOpenTime(String userOpenTime) {
        this.userOpenTime = userOpenTime;
    }

    public String getUserBdAcct() {
        return userBdAcct;
    }

    public void setUserBdAcct(String userBdAcct) {
        this.userBdAcct = userBdAcct;
    }

    public String getUserItvAcct() {
        return userItvAcct;
    }

    public void setUserItvAcct(String userItvAcct) {
        this.userItvAcct = userItvAcct;
    }

    public String getBrasDevIp() {
        return brasDevIp;
    }

    public void setBrasDevIp(String brasDevIp) {
        this.brasDevIp = brasDevIp;
    }

    public String getBrasDevDport() {
        return brasDevDport;
    }

    public void setBrasDevDport(String brasDevDport) {
        this.brasDevDport = brasDevDport;
    }

    public String getSwDevIp() {
        return swDevIp;
    }

    public void setSwDevIp(String swDevIp) {
        this.swDevIp = swDevIp;
    }

    public String getSwDevUport() {
        return swDevUport;
    }

    public void setSwDevUport(String swDevUport) {
        this.swDevUport = swDevUport;
    }

    public String getSwDevDport() {
        return swDevDport;
    }

    public void setSwDevDport(String swDevDport) {
        this.swDevDport = swDevDport;
    }

    public String getOltDevIp() {
        return oltDevIp;
    }

    public void setOltDevIp(String oltDevIp) {
        this.oltDevIp = oltDevIp;
    }

    public String getOltDevUport() {
        return oltDevUport;
    }

    public void setOltDevUport(String oltDevUport) {
        this.oltDevUport = oltDevUport;
    }

    public String getOltDevDport() {
        return oltDevDport;
    }

    public void setOltDevDport(String oltDevDport) {
        this.oltDevDport = oltDevDport;
    }

    public String getSvlan() {
        return svlan;
    }

    public void setSvlan(String svlan) {
        this.svlan = svlan;
    }

    public String getCvlan() {
        return cvlan;
    }

    public void setCvlan(String cvlan) {
        this.cvlan = cvlan;
    }

    public String getOnuLoginId() {
        return onuLoginId;
    }

    public void setOnuLoginId(String onuLoginId) {
        this.onuLoginId = onuLoginId;
    }

    public String getFamilyNgId() {
        return familyNgId;
    }

    public void setFamilyNgId(String familyNgId) {
        this.familyNgId = familyNgId;
    }

    public String getFamilyNgTypeName() {
        return familyNgTypeName;
    }

    public void setFamilyNgTypeName(String familyNgTypeName) {
        this.familyNgTypeName = familyNgTypeName;
    }

    public String getUserDevMac() {
        return userDevMac;
    }

    public void setUserDevMac(String userDevMac) {
        this.userDevMac = userDevMac;
    }

    public String getObd2Name() {
        return obd2Name;
    }

    public void setObd2Name(String obd2Name) {
        this.obd2Name = obd2Name;
    }

    public String getObd1Name() {
        return obd1Name;
    }

    public void setObd1Name(String obd1Name) {
        this.obd1Name = obd1Name;
    }

    public String getBam() {
        return bam;
    }

    public void setBam(String bam) {
        this.bam = bam;
    }

    public String getUserServicePackage() {
        return userServicePackage;
    }

    public void setUserServicePackage(String userServicePackage) {
        this.userServicePackage = userServicePackage;
    }

    @Override
    public String toString() {
        return "TopoLog{" +
                "custId='" + custId + '\'' +
                ", suerName='" + suerName + '\'' +
                ", braNo='" + braNo + '\'' +
                ", areaId='" + areaId + '\'' +
                ", contract='" + contract + '\'' +
                ", userAddr='" + userAddr + '\'' +
                ", userLevel='" + userLevel + '\'' +
                ", userOpenTime='" + userOpenTime + '\'' +
                ", userBdAcct='" + userBdAcct + '\'' +
                ", userItvAcct='" + userItvAcct + '\'' +
                ", brasDevIp='" + brasDevIp + '\'' +
                ", brasDevDport='" + brasDevDport + '\'' +
                ", swDevIp='" + swDevIp + '\'' +
                ", swDevUport='" + swDevUport + '\'' +
                ", swDevDport='" + swDevDport + '\'' +
                ", oltDevIp='" + oltDevIp + '\'' +
                ", oltDevUport='" + oltDevUport + '\'' +
                ", oltDevDport='" + oltDevDport + '\'' +
                ", svlan='" + svlan + '\'' +
                ", cvlan='" + cvlan + '\'' +
                ", onuLoginId='" + onuLoginId + '\'' +
                ", familyNgId='" + familyNgId + '\'' +
                ", familyNgTypeName='" + familyNgTypeName + '\'' +
                ", userDevMac='" + userDevMac + '\'' +
                ", obd2Name='" + obd2Name + '\'' +
                ", obd1Name='" + obd1Name + '\'' +
                ", bam='" + bam + '\'' +
                ", userServicePackage='" + userServicePackage + '\'' +
                '}';
    }

}
