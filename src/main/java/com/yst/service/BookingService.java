package com.yst.service;

import com.yst.entity.Database;
import com.yst.entity.HttpResponse;

import java.util.*;

public class BookingService implements Service{


    /**
     * 预定指定日期的房间
     *
     * @param request
     * @return
     */
    @Override
    public HttpResponse method(HashMap<String, Object> request) {
        String dateString =((HashMap<String,String>)request.get("RequestBody")).get("date");
        String name =((HashMap<String,String>)request.get("RequestBody")).get("name");
        String roomNum =((HashMap<String,String>)request.get("RequestBody")).get("roomNum");
        List<String> availableRoomList = new ArrayList<>();
        Iterator<Map.Entry<String, HashMap<String, String>>> iterator = Database.roomBookingStatus.entrySet().iterator();

        while(iterator.hasNext()){
            Map.Entry<String, HashMap<String, String>> room = iterator.next();
            if(room.getValue().containsKey(dateString)||Database.roomStatus.get(room.getKey())){
                //若某个房间某个日期有记录，则该房间在这个时间点上为不可用状态
                //若房间状态已被标记为不可用状态，则该房间也无法使用
                continue;
            }else{
                //可用列表+1
                availableRoomList.add(room.getKey());
            }
        }
        //检查该日期是否可预定
        if(!availableRoomList.contains(roomNum)){
            return new HttpResponse("该日期不可预定");
        }
        //进入预定处理，将数据落库
        Database.roomBookingStatus.get(roomNum).put(dateString,name);
        //此人有过预定记录
        if(Database.bookingLogByName.containsKey(name)){
            Database.bookingLogByName.get(name).put(dateString,roomNum);
        }else{
            //此人第一次入住
            Database.bookingLogByName.put(name,new HashMap<String,String>(){{
                put(dateString,roomNum);
            }});
        }
        return new HttpResponse("预定成功");
    }
}
