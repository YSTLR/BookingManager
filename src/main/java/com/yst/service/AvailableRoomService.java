package com.yst.service;

import com.yst.entity.Database;
import com.yst.entity.HttpResponse;

import java.util.*;

/**
 * get available rooms by given time
 * @author Yan Siting
 */
public class AvailableRoomService implements Service{


    /**
     * get available rooms by given time
     * P.S.: in this project , return a standard response is correct,
     * work of analysis should be done by a standard class
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
                //if we can find record of the room in a given date,then this room is not available in this time
                //if this room is marked to not used ,then this room is not available too
                continue;
            }else{
                //add a available room to availableRoomList
                availableRoomList.add(room.getKey());
            }
        }


        return new HttpResponse("{available room is ：#}".replaceAll("#",availableRoomList.toString()));
    }
}
