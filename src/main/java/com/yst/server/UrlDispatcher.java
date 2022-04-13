package com.yst.server;

import com.yst.service.Service;

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


    public static Service handleRequest(Map<String,Object> param) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        Class clas = null;
        Object o = null;
        String url =(String) param.get("url");
        switch (url){
            case availableRoom :
                clas = Class.forName(availableRoomService);
                o = clas.newInstance();
                break;
            case booking:
                clas = Class.forName(bookingService);
                o = clas.newInstance();
                break;
            default:
                clas = Class.forName(defaultService);
                o = clas.newInstance();
                break;
        }
        return (Service)o;
    }
}
