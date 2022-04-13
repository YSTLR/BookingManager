package com.yst.entity;

import com.yst.entity.config.DateBaseConfig;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Yan Siting
 */
public class Database {

    /**
     * Database of booking rooms:
     * First string is room number,
     * Second string is date,
     * Last string is customer name
     */
    public static ConcurrentHashMap<String,HashMap<String,String>> roomBookingStatus ;

    /**
     * Datebase of room status
     * the closed room's is false
     */
    public static HashMap<String,Boolean> roomStatus;

    /**
     * Second Database of booking rooms:
     * First string is customer name,
     * Second string is date,
     * Last string is room number
     * P.S.:trade space for time,sacrifice memory to improve efficiency of retrieval
     */
    public static HashMap<String, LinkedList<String>> bookingLogByName ;


    /**
     * build database by initialization
     * @param config
     */
    public static void build(DateBaseConfig config){

        roomBookingStatus = new ConcurrentHashMap(config.getInitRoomNum()*2){{
            for(int i=0 ; i<config.getInitRoomNum() ; i++){
                put(i+"",new HashMap<String,String>());
            }
        }};
        roomStatus = new HashMap(config.getInitRoomNum()){{
            for(int i = 0 ; i<config.getInitRoomNum() ; i++){
                put(i+"",false);
            }
        }};
        bookingLogByName = new HashMap<String,LinkedList<String>>();
    }

}
