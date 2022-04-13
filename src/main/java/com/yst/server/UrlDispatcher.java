package com.yst.server;

import com.yst.service.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yan Siting
 * return instance of service according to different url
 * the correct method is to implant java annotation,and to scan the package,
 * or use xml or yaml setting files to handle the relationship between url and service
 */
public class UrlDispatcher {

    /**
     * default service,fake 404 handler
     */
    private static final String defaultService = "com.yst.service.DefaultService" ;

    /**
     * get available rooms by given time
     */
    private static final String availableRoom = "/availableRoom" ;
    private static final String availableRoomService = "com.yst.service.AvailableRoomService" ;

    /**
     * booking rooms
     */
    private static final String booking = "/booking" ;
    private static final String bookingService = "com.yst.service.BookingService" ;

    /**
     * get booking logs
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
     * request dispatcher
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
