package com.nudt.query.controller;

import com.nudt.query.entity.MenuItem;
import com.nudt.query.entity.MonitorRecord;
import com.nudt.query.util.queryUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2018/11/16.
 */
@Controller
public class FrontController {

    private static Logger logger = LoggerFactory.getLogger(FrontController.class);

    @RequestMapping(value={"/", "index"}, method={RequestMethod.GET})
    public ModelAndView index(HttpServletRequest request)
    {
        ModelAndView modelAndView = new ModelAndView();
        // 1. 访问地址
        addServerAddress(request, modelAndView);
        // 2. 顶部菜单
        MenuItem menuItem = addTopMenuList(modelAndView);
        // 3. 左侧菜单
        String itemName = (menuItem == null ? "" : menuItem.getItemName());
        addLeftItemList(itemName, modelAndView);
        // 4. 中间视图
        modelAndView.addObject("pageUrl", "serviceInterfaces");
        // 5. 视图名称
        modelAndView.setViewName("index");
        return modelAndView;
    }

    private void addServerAddress(HttpServletRequest request, ModelAndView modelAndView) {
        String protocol = "";
        String host = "";
        int port = 8080;
        try {
            protocol = request.getScheme();
            host = InetAddress.getLocalHost().getHostAddress();
            port = request.getLocalPort();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        modelAndView.addObject("protocol", protocol);
        modelAndView.addObject("host", host);
        modelAndView.addObject("port", port);
    }

    /**
     * 添加顶部菜单列表
     * @param modelAndView
     */
    private MenuItem addTopMenuList(ModelAndView modelAndView) {
        List<MenuItem> menuList = new ArrayList<>();
        menuList.add(new MenuItem("系统帮助", "/systemHelp", "glyphicon glyphicon-book"));
        menuList.add(new MenuItem("系统监控", "/systemMonitor", "glyphicon glyphicon-signal"));
        menuList.add(new MenuItem("系统设置", "/systemSettings", "glyphicon glyphicon-wrench"));
        menuList.add(new MenuItem("系统服务", "/systemService", "glyphicon glyphicon-th"));
        modelAndView.addObject("menuList", menuList);
        if(menuList.size() > 0){
            return menuList.get(menuList.size() - 1);
        }else{
            return null;
        }
    }

    /**
     * 添加左侧菜单栏选项列表
     * @param itemName
     * @param modelAndView
     */
    private void addLeftItemList(String itemName, ModelAndView modelAndView) {
        List<MenuItem> menuList = new ArrayList<>();
        if(!StringUtils.isEmpty(itemName)){
            switch (itemName){
                case "系统服务":
                    menuList.add(new MenuItem("服务接口测试", "serviceInterfaces", "glyphicon glyphicon-chevron-right"));
                    modelAndView.addObject("itemList", menuList);
                    break;
                case "系统设置":
                    menuList.add(new MenuItem("配置修改", "settingUpdate", "glyphicon glyphicon-chevron-right"));
                    menuList.add(new MenuItem("样式修改", "stylesUpdate", "glyphicon glyphicon-chevron-right"));
                    modelAndView.addObject("itemList", menuList);
                    break;
                case "系统监控":
                    menuList.add(new MenuItem("系统监控", "systemMonitor", "glyphicon glyphicon-chevron-right"));
                    modelAndView.addObject("itemList", menuList);
                    List<MonitorRecord> recordList = new ArrayList<>();
                    Random rnd = new Random();
                    for(int i = 0; i < 10; i++){
                        recordList.add(new MonitorRecord(queryUtils.common_sdf.format(new Date()), rnd.nextInt(80)/100 + 0.2,
                                rnd.nextInt(70)/100 + 0.3, rnd.nextInt(75)/100 + 0.25, rnd.nextInt(2)));
                    }
                    modelAndView.addObject("recordList", recordList);
                    break;
                case "系统帮助":
                    menuList.add(new MenuItem("系统简介", "systemProfile", "glyphicon glyphicon-chevron-right"));
                    menuList.add(new MenuItem("帮助文档", "systemHelp", "glyphicon glyphicon-chevron-right"));
                    menuList.add(new MenuItem("常见问题", "systemProblems", "glyphicon glyphicon-chevron-right"));
                    modelAndView.addObject("itemList", menuList);
                    break;
                default:
                    break;
            }
            if(menuList.size() > 0){
                MenuItem menuItem = menuList.get(0);
                modelAndView.addObject("pageUrl", menuItem.getItemUrl());
            }
        }else{
            menuList.add(new MenuItem("系统动态", "systemNews", "glyphicon glyphicon-chevron-right"));
        }
        modelAndView.addObject("profileName", itemName);
    }

    /**
     * 获取左菜单列表
     * @return
     */
    @RequestMapping(value={"/getLeftItemList"}, method={RequestMethod.GET})
    public ModelAndView getLeftItemList(HttpServletRequest request,
                                      @RequestParam("itemName")String itemName)
    {
        ModelAndView modelAndView = new ModelAndView();
        addServerAddress(request, modelAndView);
        // 1. 顶部菜单
        addTopMenuList(modelAndView);
        // 2. 左侧菜单
        addLeftItemList(itemName, modelAndView);
        // 3. 视图名称
        modelAndView.setViewName("index");
        return modelAndView;
    }

    /**
     * 获取主页面地址
     * @return
     */
    @RequestMapping(value={"/getRightMainUrl"}, method={RequestMethod.GET})
    public ModelAndView getRightMainUrl(HttpServletRequest request,
                                        @RequestParam("itemName")String itemName,
                                        @RequestParam("pageUrl")String pageUrl)
    {
        ModelAndView modelAndView = new ModelAndView();
        addServerAddress(request, modelAndView);
        addTopMenuList(modelAndView);
        addLeftItemList(itemName, modelAndView);
        if(!StringUtils.isEmpty(pageUrl)){
            modelAndView.addObject("pageUrl", pageUrl);
        }
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
