package com.nudt.query.controller;

import com.google.gson.Gson;
import com.nudt.query.Const.IndexType;
import com.nudt.query.entity.RangeEntity;
import com.nudt.query.entity.ReportEntity;
import com.nudt.query.service.ReportQueryService;
import com.nudt.query.util.queryUtils;
import io.searchbox.core.SearchResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

/**
 * Created by haibozhang on 2018/11/9.
 */
@Controller
@Api(value = "QueryController", description = "日志系统对外服务接口")
public class QueryController {
    // swagger访问地址： http://localhost:8080/swagger-ui.html
    private static Logger logger = LoggerFactory.getLogger(QueryController.class);

    @Autowired
    private ReportQueryService reportQueryService;
   /* @Autowired
    private ReportJdbc reportJdbc;*/
    @Autowired
    private Gson gson;

    /**
     * 查询所有可监控的PON
     * @param pageSize 页码大小
     * @param pageNum  页码
     * @return
     */
    @ApiOperation(value = "查询所有可监控的PON", notes = "查询所有可监控的PON")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = true, dataType = "int", paramType="query"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType="query")
    })
    @RequestMapping(value = {"/show/pon"},  method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String getMonitorAvailableOLTDevice(@RequestParam("pageSize") int pageSize,
                                               @RequestParam("pageNum") int pageNum){
        return reportQueryService.getMonitorAvailableOLTDevice(null, null, pageNum, pageSize);
    }

    /**
     * 根据地域查询所有可监控的PON
     * @param areaId    地域
     * @param pageSize  页码大小
     * @param pageNum   页码
     * @return
     */
    @ApiOperation(value = "根据地域查询所有可监控的PON", notes = "根据地域查询所有可监控的PON")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaId", value = "地域", required = true, dataType = "int", paramType="path"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = true, dataType = "int", paramType="query"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType="query")
    })
    @RequestMapping(value = {"/show/pon/{areaId}"},  method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String getAvailableOLTDeviceByAreaId01(@PathVariable("areaId") String areaId,
                                                  @RequestParam("pageSize") int pageSize,
                                                  @RequestParam("pageNum") int pageNum){
        return reportQueryService.getMonitorAvailableOLTDevice(areaId, null, pageNum, pageSize);
    }

    /**
     * 根据地域查询所有可监控的PON
     * @param areaId    地域
     * @param pageSize  页码大小
     * @param pageNum   页码
     * @return
     */
    @ApiOperation(value = "根据地域查询所有可监控的PON", notes = "根据地域查询所有可监控的PON")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaId", value = "地域", required = true, dataType = "int", paramType="path"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = true, dataType = "int", paramType="query"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType="query")
    })
    @RequestMapping(value = {"/show/pon/areaId/{areaId}"},  method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String getAvailableOLTDeviceByAreaId02(@PathVariable("areaId") String areaId,
                                                  @RequestParam("pageSize") int pageSize,
                                                  @RequestParam("pageNum") int pageNum){
        return reportQueryService.getMonitorAvailableOLTDevice(areaId, null, pageNum, pageSize);
    }

    /**
     * 根据账号查询所有可监控的PON
     * @param account
     * @param pageSize
     * @param pageNum
     * @return
     */
    @ApiOperation(value = "根据账号查询所有可监控的PON", notes = "根据账号查询所有可监控的PON")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "账号", required = true, dataType = "int", paramType="path"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = true, dataType = "int", paramType="query"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType="query")
    })
    @RequestMapping(value = {"/show/pon/account/{account}"},  method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String getAvailableOLTDeviceByAccount(@PathVariable("account") String account,
                                                 @RequestParam("pageSize") int pageSize,
                                                 @RequestParam("pageNum") int pageNum){
        return reportQueryService.getMonitorAvailableOLTDevice(null, account, pageNum, pageSize);
    }

    /**
     * 根据地域和账号查询所有可监控的PON
     * @param areaId
     * @param account
     * @param pageSize
     * @param pageNum
     * @return
     */
    @ApiOperation(value = "根据地域和账号查询所有可监控的PON", notes = "根据地域和账号查询所有可监控的PON")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaId", value = "地域", required = true, dataType = "int", paramType="path"),
            @ApiImplicitParam(name = "account", value = "账号", required = true, dataType = "int", paramType="path"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = true, dataType = "int", paramType="query"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType="query")
    })
    @RequestMapping(value = {"/show/pon/{areaId}/{account}"},  method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String getAvailableOLTDevice(@PathVariable("areaId") String areaId,
                                        @PathVariable("account") String account,
                                        @RequestParam("pageSize") int pageSize,
                                        @RequestParam("pageNum") int pageNum){
        return reportQueryService.getMonitorAvailableOLTDevice(areaId, account, pageNum, pageSize);
    }

    /**
     *  PON系统监控员需查看OLT设备端口监控报告时，需（通过URL接口）提交调用条件单据
     *  OLT设备端口监控报告调用格式为：http://服务器地址:端口/show/OLT设备IP和端口/账号类型
     *  OLT设备IP和端口: A_A_A_A：表示olt的IP地址（将“.”换为“_”)、B_B_B_B：表示PON端口号（将“-”换为“_”)
     *  账号类型：“all”表示全部账号，“iptv”表示itv账号，“net”表示宽带账号
     *  接口：查询指定PON端口号的在线用户统计表
     */

    /**
     * 根据OLT设备IP端口号查询PON端口监控报告
     * @param oltDevIpPort OLT设备IP端口号，例如：A_A_A_A_B_B_B_B，A_A_A_A：表示olt的IP地址（将“.”换为“_”)、B_B_B_B：表示PON端口号（将“-”换为“_”)
     * @return JSON
     */
    @ApiOperation(value = "根据OLT设备IP端口号查询PON端口监控报告", notes = "根据OLT设备IP端口号查询PON端口监控报告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oltDevIpPort",
                    value = "OLT设备IP端口号，例如：A_A_A_A_B_B_B_B，A_A_A_A：表示olt的IP地址（将“.”换为“_”)、B_B_B_B：表示PON端口号（将“-”换为“_”)",
                    required = true, dataType = "string", paramType="path"),
    })
    @RequestMapping(value = {"/show/{oltDevIpPort}"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String getOLTDevicePortMonitorReport(@PathVariable("oltDevIpPort") String oltDevIpPort){
        if(StringUtils.isEmpty(oltDevIpPort)){
            return "URL接口中的参数[OLT设备IP和端口]不能为空！";
        }
        String[] ipPort = oltDevIpPort.split("_"); // OLT设备地址和端口
        if(ipPort.length != 8){
            return "URL接口中的参数[OLT设备IP和端口]格式存在问题";
        }
        String oltDevIp = ipPort[0] + "." + ipPort[1] + "." + ipPort[2] + "." + ipPort[3];        // OLT设备地址
        String oltDevDport = ipPort[4] + "-" + ipPort[5] + "-" + ipPort[6] + "-" + ipPort[7];     // OLT设备下行端口（PON端口号）
        String reportData = reportQueryService.getPONRunningStateReport(oltDevIp, oltDevDport, "all");
//        reportJdbc.save(new ReportEntity(new Date(), reportData));
        return reportData;
    }

    /**
     * 根据OLT设备IP端口号和账号类型查询PON端口监控报告
     * @param oltDevIpPort  OLT设备IP和端口号，例如：A_A_A_A_B_B_B_B，A_A_A_A：表示olt的IP地址（将“.”换为“_”)、B_B_B_B：表示PON端口号（将“-”换为“_”)
     * @param userAcctType  账号类型：“all”表示全部账号，“iptv”表示itv账号，“net”表示宽带账号
     * @return JSON
     */
    @ApiOperation(value = "根据OLT设备IP端口号和账号类型查询PON端口监控报告", notes = "根据OLT设备IP端口号和账号类型查询PON端口监控报告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oltDevIpPort",
                    value = "OLT设备IP和端口号，例如：A_A_A_A_B_B_B_B，A_A_A_A：表示olt的IP地址（将“.”换为“_”)、B_B_B_B：表示PON端口号（将“-”换为“_”)",
                    required = true, dataType = "string", paramType="path"),
            @ApiImplicitParam(name = "userAcctType",
                    value = "账号类型：“all”表示全部账号，“iptv”表示itv账号，“net”表示宽带账号",
                    required = true, dataType = "string", paramType="path")
    })
    @RequestMapping(value = {"/show/{oltDevIpPort}/{userAcctType}"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String getOLTDevicePortMonitorReport(@PathVariable("oltDevIpPort") String oltDevIpPort,
                                     @PathVariable("userAcctType") String userAcctType){
        if(StringUtils.isEmpty(oltDevIpPort)){
            return "URL接口中的参数[OLT设备IP和端口]不能为空！";
        }
        String[] ipPort = oltDevIpPort.split("_"); // OLT设备地址和端口
        if(ipPort.length != 8){
            return "URL接口中的参数[OLT设备IP和端口]格式存在问题";
        }
        String oltDevIp = ipPort[0] + "." + ipPort[1] + "." + ipPort[2] + "." + ipPort[3];        // OLT设备地址
        String oltDevDport = ipPort[4] + "-" + ipPort[5] + "-" + ipPort[6] + "-" + ipPort[7];     // OLT设备下行端口（PON端口号）
        String reportData = reportQueryService.getPONRunningStateReport(oltDevIp, oltDevDport, userAcctType);
//        reportJdbc.save(new ReportEntity(new Date(), reportData));
        return reportData;
    }

    @RequestMapping(value = "/getAllReport", method = RequestMethod.GET)
    @ResponseBody
    public String getAllReport(){
//        List<ReportEntity> list = reportJdbc.findAll();
        List<ReportEntity> list = new ArrayList<>();
        return gson.toJson(list);
    }

    /**
     * 测试
     * @return
     */
    @RequestMapping(value="/test", method={RequestMethod.GET})
    @ResponseBody
    public String test(){
        String result = "";
        try {
           /* Map<String, Object> map = new HashMap<>();
            String index = "index_topo";
            String type = "topo_type";
            map.put("oltDevIp", "134.180.106.39");
            List<TopoLog> topoLogList = reportQueryService.topoQuery(index, type, map, -1, -1);
            topoLogList.forEach(System.out :: println);
            if(topoLogList.size() > 0){
                return gson.toJson(topoLogList.get(0));
            }else{
                return "empty";
            }*/
            List<String> indexList = new ArrayList<>();
            indexList.add("index_online");
            indexList.add("index_offline");
            List<String> typesList = new ArrayList<>();
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("userId", "07349738134");
            Date end = new Date();
            Date countTime = queryUtils.dateFormatToDate(end, IndexType.ONLINE); // 统计时间
            Date start = queryUtils.getDateBefore(end, 5);
            RangeEntity rangeEntity = null;
            SearchResult searchResult = reportQueryService.getMultiIndexSearchResult(indexList, typesList,
                    paramMap, rangeEntity, 0, 135);
            result = searchResult.getJsonString();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
            result = "查询失败，错误提示：" + e.getLocalizedMessage();
        }
        return result;
    }

}
