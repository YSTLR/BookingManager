package com.yst.service;

import com.yst.entity.Constant;
import com.yst.entity.Database;
import com.yst.entity.HttpResponse;

import java.util.*;

/**
 * booking rooms
 * @author Yan Siting
 */
public class BookingService implements Service{


    /**
     * booking a room
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
        if(Integer.parseInt(roomNum)>Constant.INIT_ROOM_NUM||Integer.parseInt(roomNum)<=0){
            return new HttpResponse("Incorrect room number");
        }
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
        //check this date
        if(!availableRoomList.contains(roomNum)){
            return new HttpResponse("This room is in used");
        }
        //enter the process of ordering,record data
        Database.roomBookingStatus.get(roomNum).put(dateString,name);
        //write log table
        //this custom has ordered log
        if(Database.bookingLogByName.containsKey(name)){
            Database.bookingLogByName.get(name).add(dateString+":"+roomNum);
        }else{
            //first time of this custom
            Database.bookingLogByName.put(name,new LinkedList<String>(){{
                add(dateString+":"+roomNum);
            }});
        }
        return new HttpResponse("Success!");
    }
}
