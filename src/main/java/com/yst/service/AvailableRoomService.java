package com.yst.service;

import com.yst.entity.Database;
import com.yst.entity.HttpResponse;

import java.util.*;

/**
 * 获取可用房间号
 * @author Yan Siting
 */
public class AvailableRoomService implements Service{


    /**
     * 根据指定的日期，查询可用的房间号
     * 备注：其实这里做的不好，应该返回一个标准格式的内容，内容的解析工作交给统一的实现类去实现
     * 这里采用了半硬编码方式，方便实现但不利于代码复用及解耦
     * @param request
     * @return
     */
    @Override
    public HttpResponse method(HashMap<String,Object> request) {
        String dateString =((HashMap<String,String>)request.get("RequestBody")).get("date");
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


        return new HttpResponse("{可用房间有：#}".replaceAll("#",availableRoomList.toString()));
    }
}
