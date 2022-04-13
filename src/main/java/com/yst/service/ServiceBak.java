package com.yst.service;

import com.yst.Util;
import com.yst.entity.Database;

import java.util.*;

/**
 *先不考虑输入时间段的api接口
 * @author Yan Siting
 */
@Deprecated
public class ServiceBak {



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

}
