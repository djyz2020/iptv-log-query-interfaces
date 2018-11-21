package com.nudt.query.service;

import com.google.gson.Gson;
import com.nudt.query.Const.Const;
import com.nudt.query.Const.IndexType;
import com.nudt.query.entity.*;
import com.nudt.query.util.queryUtils;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.io.IOException;
import java.util.*;

/**
 * Created by haibozhang on 2018/11/9.
 */
@Service
public class ReportQueryService extends CommonQueryService{
    private static Logger logger = LoggerFactory.getLogger(ReportQueryService.class);

    @Autowired
    private Gson gson;

    /**
     * 根据地域和账号查询可监控的OLT设备
     * @param areaId    地域
     * @param account   账号
     * @param pageNum   页码
     * @param pageSize  页码大小
     * @return
     */
    public String getMonitorAvailableOLTDevice(String areaId, String account, int pageNum, int pageSize){
        long startTime = System.currentTimeMillis();
        List<TopoLog> topoLogList = new ArrayList<>();
        Map<String, Object> paramMap = new HashMap<>();
        if(!StringUtils.isEmpty(areaId)){
            paramMap.put("areaId", areaId);
        }
        double totalcount = 0;
        try {
            if(!StringUtils.isEmpty(account)){
                // 宽带账号
                paramMap.put("userBdAcct", account);
                totalcount += countQuery(Const.INDEX_TOPO, Const.TYPE_TOPO, paramMap, -1);
                topoLogList.addAll(topoQuery(Const.INDEX_TOPO, Const.TYPE_TOPO, paramMap, (pageNum - 1) * pageSize + 1, pageSize));
                // IPIV账号
                paramMap.remove("userBdAcct");
                if(account.indexOf("@VOD") > -1){
                    paramMap.put("userItvAcct", account);
                }else{
                    paramMap.put("userItvAcct", account + "@VOD");
                }
            }
            totalcount += countQuery(Const.INDEX_TOPO, Const.TYPE_TOPO, paramMap, -1);
            topoLogList.addAll(topoQuery(Const.INDEX_TOPO, Const.TYPE_TOPO, paramMap, (pageNum - 1) * pageSize + 1, pageSize));
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.debug("查询到的记录总数为：[" + totalcount + "]条, 耗时：[" + (System.currentTimeMillis() - startTime) + "]ms");
        String result =  gson.toJson(topoLogList);
        System.out.println(result);
        return result;
    }

    /**
     * 根据OLT设备IP地址和下行端口获取PON运行状态【监控报告】
     * @param oltDevIp      OLT设备IP地址
     * @param oltDevDport   OLT设备下行端口号
     * @param userAcctType  账号类型：“all”表示全部账号，“iptv”表示itv账号，“net”表示宽带账号
     * @return
     */
    public String getPONRunningStateReport(String oltDevIp, String oltDevDport, String userAcctType){
        CountEntity countEntity = new CountEntity(); // 统计结果
        List<String> userAcct_BD = new ArrayList<>();
        List<String> userAcct_IPTV = new ArrayList<>();
        Map<String, Object> mustMap = new HashMap<>();
        Map<String, Object> mustNotMap = new HashMap<>();
        try {
            mustMap.put("oltDevIp", oltDevIp);
            mustMap.put("oltDevDport", oltDevDport);
            List<TopoLog> topoLogList = topoQuery(Const.INDEX_TOPO, Const.TYPE_TOPO, mustMap, -1, -1);
            // a. 注册的宽带账号数
            int userCountBD = 0;
            for(TopoLog topoLog : topoLogList){
                if(!StringUtils.isEmpty(topoLog.getUserBdAcct())){
                    userCountBD++;
                    userAcct_BD.add(topoLog.getUserBdAcct());
                }
            }
            countEntity.setUserCountBD(userCountBD);
            // b. 注册的IPTV用户数
            int userCountIPTV = 0;
            String tmpUserId = "";
            for(TopoLog topoLog : topoLogList){
                if(!StringUtils.isEmpty(topoLog.getUserItvAcct())){
                    userCountIPTV++;
                    tmpUserId = topoLog.getUserItvAcct();
                    userAcct_IPTV.add(tmpUserId.substring(0, tmpUserId.indexOf("@VOD")));
                }
            }
            countEntity.setUserCountIPTV(userCountIPTV);
            //----------------------------------------------------------------------------------//
//            Date end = queryUtils.dateFormatToDate("20180914235911", IndexType.OFFLINE);  // 测试用
            Date end = new Date();
            Date start = queryUtils.getDateBefore(end, 5);  //统计5天的数据
            countEntity.setCountTime(queryUtils.dateFormatToString(end, IndexType.ONLINE)); // 当前统计时间
            RangeEntity rangeEntity = new RangeEntity("time", queryUtils.dateFormatToString(start, IndexType.ONLINE),
                    true, queryUtils.dateFormatToString(end, IndexType.ONLINE), true);
            // c. 在线的宽带账号数
            long startTime = System.currentTimeMillis();
            int onlineCountBD = 0;
            mustNotMap.put("idType", "vod");  // 宽带账号：数字
            List<AccountEntity> bdAccountList = new ArrayList<>();
            for(String userAcct : userAcct_BD){
                mustMap = new HashMap<>();
                mustMap.put("userId", userAcct);
                List<Entity> list = getEntities(mustMap, mustNotMap, rangeEntity);
                if(list != null){
                    if(list.size() > 0){
                        Entity entity = list.get(list.size() - 1);
                        if(entity instanceof OnlineLog){
                            onlineCountBD++;
                        }
                    }
                    bdAccountList.add(new AccountEntity(userAcct, list));
                }
            }
            if(!"iptv".equals(userAcctType)){
                countEntity.setOnlineBDList(bdAccountList);
            }
            countEntity.setOnlineCountBD(onlineCountBD);
            // d. 在线的IPTV账号数
            int onlineCountIPTV = 0;
            mustMap.put("idType", "vod");  // IPTV账号
            List<AccountEntity> iptvAccountList = new ArrayList<>();
            for(String userAcct : userAcct_IPTV){
                mustMap = new HashMap<>();
                mustMap.put("userId", userAcct);
                List<Entity> list = getEntities(mustMap, null, rangeEntity);
                if(list != null){
                    if(list.size() > 0){
                        Entity entity = list.get(list.size() - 1);
                        if(entity != null && entity instanceof OnlineLog){
                            onlineCountIPTV++;
                        }
                    }
                    iptvAccountList.add(new AccountEntity(userAcct, list));
                }
            }
            if(!"net".equals(userAcctType)){
                countEntity.setOnlineIPTVList(iptvAccountList);
            }
            countEntity.setOnlineCountIPTV(onlineCountIPTV);
            countEntity.setOltDevIp(oltDevIp);
            countEntity.setOltDevDport(oltDevDport);
            logger.debug("[PON监控报告] 查询处理花费时间：[" + (System.currentTimeMillis() - startTime) + "]ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gson.toJson(countEntity);
    }

    // 查询指定账号（BD账号或IPTV账号）指定时间段（5天内）的上下线信息
    private List<Entity> getEntities(Map<String, Object> mustMap, Map<String, Object> mustNotMap,
                                     RangeEntity rangeEntity) throws IOException {
        List<OnlineLog> onlineLogList = rangeOnlineQuery(Const.INDEX_ONLINE, Const.TYPE_ONLINE, mustMap,
                mustNotMap, rangeEntity, null);
        List<OfflineLog> offlineLogList = rangeOfflineQuery(Const.INDEX_OFFLINE, Const.TYPE_OFFLINE, mustMap,
                mustNotMap, rangeEntity, null);
        List<Entity> list = new ArrayList<>();
        list.addAll(onlineLogList);
        list.addAll(offlineLogList);
        Collections.sort(list);
        return list;
    }

    /**
     * 根据OLT设备IP地址和下行端口查询所有账号
     * @param oltDevIp
     * @param oltDevDport
     * @return
     */
    public List<TopoLog> accountQuery(String oltDevIp, String oltDevDport){
        return accountQuery(oltDevIp, oltDevDport, "all");
    }

    /**
     * 根据OLT设备IP地址和下行端口查询账号
     * @param oltDevIp        OLT设备地址: A.A.A.A
     * @param oltDevDport     PON端口号：B-B-B-B
     * @param userAcctType    账号类型：“all”表示全部账号，“iptv”表示itv账号，“net”表示宽带账号
     * @return
     */
    public List<TopoLog> accountQuery(String oltDevIp, String oltDevDport, String userAcctType) {
        long startTime = System.currentTimeMillis();
        List<TopoLog> topoLogList = new ArrayList<>();
        Map<String, Object> mustRegexp = new HashMap<>();      // 字段中必须有xxx
        Map<String, Object> mustNotRegexp = new HashMap<>();   // 字段中必须没有xxx
        mustRegexp.put("oltDevIp", oltDevIp);
        mustRegexp.put("oltDevDport", oltDevDport);
        try {
           switch (userAcctType){
               case "all":
                   topoLogList = topoQuery(Const.INDEX_TOPO, Const.INDEX_TOPO, mustRegexp, -1, -1);
                   break;
               case "iptv":
                   mustRegexp.put("userBdAcct", "*@VOD");  // 互联协议电视账号：数字 + @VOD
                   topoLogList = getRegexpResult(Const.INDEX_TOPO, Const.INDEX_TOPO, mustRegexp, mustNotRegexp);
                   break;
               case "net":
                   mustNotRegexp.put("userBdAcct", "*@VOD");  // 宽带账号：数字
                   topoLogList = getRegexpResult(Const.INDEX_TOPO, Const.INDEX_TOPO, mustRegexp, mustNotRegexp);
                   break;
           }
        } catch (Exception e){
           e.printStackTrace();
        }
//        List<TopoLog> newList = getTopoLogs(oltDevIp, oltDevDport, topoLogList);
//        logger.debug("查询到的记录总数为：[" + newList.size() + "]条, 耗时：[" + (System.currentTimeMillis() - startTime)/1000 + "]s");
//        return newList;
        logger.debug("查询到的记录总数为：[" + topoLogList.size() + "]条, 耗时：[" + (System.currentTimeMillis() - startTime)/1000 + "]s");
        return topoLogList;
    }

    // 过滤记录
    private List<TopoLog> getTopoLogs(String oltDevIp, String oltDevDport, List<TopoLog> topoLogList) {
        List<TopoLog> newList = new ArrayList<>();
        for(TopoLog topoLog : topoLogList){
            boolean addFlag = true;
            if(!oltDevDport.equals(topoLog.getOltDevDport())){
                addFlag = false;
            }
            if(!oltDevIp.equals(topoLog.getOltDevIp())){
                addFlag = false;
            }
            if(addFlag){
                newList.add(topoLog);
            }
        }
        return newList;
    }

}
