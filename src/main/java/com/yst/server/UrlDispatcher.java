package com.yst.server;

import com.yst.service.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yan Siting
 * 根据访问url返回对应的service实例
 * 正常做法应该是自定义注解再扫描包，或者使用外部配置文件指定url和路径的映射关系，此处由于时间问题先硬编码实现
 */
public class UrlDispatcher {

    /**
     * 根据指定日期获取可用房间
     */
    private static final String defaultService = "com.yst.service.DefaultService" ;

    /**
     * 根据指定日期获取可用房间
     */
    private static final String availableRoom = "/availableRoom" ;
    private static final String availableRoomService = "com.yst.service.AvailableRoomService" ;

    /**
     * 预定房间
     */
    private static final String booking = "/booking" ;
    private static final String bookingService = "com.yst.service.BookingService" ;

    /**
     * 查询历史记录
     */
    private static final String customLog = "/customLog" ;
    private static final String customLogService = "com.yst.service.CustomService" ;

    private static HashMap<String,String> urlMap;

    static {
        urlMap = new HashMap<String,String>(){{
            put("defaultService",defaultService);
            put("/availableRoom","com.yst.service.AvailableRoomService");
            put("/booking","com.yst.service.BookingService");
            put("/customLog","com.yst.service.CustomService");
        }};
    }


    /**
     * 服务分发器
     * @param param
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static Service handleRequest(Map<String,Object> param) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        Class clas = null;
        Object o = null;
        String url =(String) param.get("url");
        clas = Class.forName(urlMap.get(url));
        o = clas.newInstance();
        return (Service)o;
    }
}
