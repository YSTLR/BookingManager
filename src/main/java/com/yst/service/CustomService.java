package com.yst.service;

import com.yst.entity.Database;
import com.yst.entity.HttpResponse;

import java.util.*;

/**
 * get history of log
 * @author Yan Siting
 */
public class CustomService implements Service{

    /**
     * get history of log
     *
     * @param request
     * @return
     */
    @Override
    public HttpResponse method(HashMap<String, Object> request) {

        //get request parameters
        String name =((HashMap<String,String>)request.get("RequestBody")).get("name");

        //check ordering history of this custom
        if(!Database.bookingLogByName.containsKey(name)){
            return new HttpResponse("No history of this custom");
        }else{
            StringBuffer stringBuffer = new StringBuffer();
            LinkedList<String> log= Database.bookingLogByName.get(name);
            //export history log,and build response body
            for(String str:log){
                stringBuffer.append(str+",");
            }
            String result = new String(stringBuffer);
            result = result.substring(0,result.length()-1);

            //json library is not allowed ,so I'm splicing response message by myself
            return new HttpResponse("{"+result+"}");

        }
    }
}
