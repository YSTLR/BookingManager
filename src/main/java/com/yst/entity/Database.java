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
     * 记录客房预定记录，String1房间号，String2日期，String3姓名
     */
    public static ConcurrentHashMap<String,HashMap<String,String>> roomBookingStatus ;

    /**
     * 客房是否关闭的标志,被关闭的房间值为false
     */
    public static HashMap<String,Boolean> roomStatus;

    /**
     * 记录预定历史记录的集合(按姓名记录)，String表示姓名，String2表示日期，String3表示房间号
     * 备注：存在的意义：空间换时间,根据姓名检索的时候速度加快
     */
    public static HashMap<String, LinkedList<String>> bookingLogByName ;


    /**
     * 根据初始值，构建数据库
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
