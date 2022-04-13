package com.yst.service;

import com.yst.Util;
import com.yst.entity.Database;

import java.util.*;

/**
 * @author Yan Siting
 */
public class Service1 {



    /**
     * 根据指定的日期范围，给出结果消息
     * @param startDateString
     * @param endDateString
     * @return
     */
    public String getAvailableRoom(String startDateString,String endDateString){

        String[] dateRange = Util.getDateRange(startDateString,endDateString);
        List<String> availableRoomList = new ArrayList<>();
        Iterator<Map.Entry<String, HashMap<String, String>>> iterator = Database.roomBookingStatus.entrySet().iterator();
        loop1:while(iterator.hasNext()){
            Map.Entry<String, HashMap<String, String>> room = iterator.next();
            loop2:for(int i =0 ; i<dateRange.length ; i++){
                        if(room.getValue().containsKey(dateRange[i])){
                            continue loop1;
                        }
            }
            availableRoomList.add(room.getKey());
        }
        return "该时间段内，可连续入住的房间有："+availableRoomList;
    }

    /**
     * 根据日期范围预定房间
     * @param name
     * @param roomNum
     * @param startDate
     * @param endDate
     * @return
     */
    public String bookingRoom(String name,int roomNum,String startDate,String endDate){

        return null;
    }

    /**
     * 预定某一具体日期的房间
     * @param name
     * @param roomNum
     * @param date
     * @return
     */
    public String bookingRoom(String name,int roomNum,String date){

        return null;
    }

}
