package com.yst.service;

import com.yst.entity.Database;
import com.yst.entity.HttpResponse;

import java.util.*;

/**
 * 查询客人预定历史
 * @author Yan Siting
 */
public class CustomService implements Service{

    /**
     * 查询客人预定历史
     *
     * @param request
     * @return
     */
    @Override
    public HttpResponse method(HashMap<String, Object> request) {

        //获取请求参数
        String name =((HashMap<String,String>)request.get("RequestBody")).get("name");

        //校验该用户有无预定历史记录
        if(!Database.bookingLogByName.containsKey(name)){
            return new HttpResponse("该客人无历史预定记录");
        }else{
            StringBuffer stringBuffer = new StringBuffer();
            LinkedList<String> log= Database.bookingLogByName.get(name);
            //将日志导出，构建响应体
            for(String str:log){
                stringBuffer.append(str+",");
            }
            String result = new String(stringBuffer);
            result = result.substring(0,result.length()-1);

            //丑陋的代码，没有引入json库，也没有自己实现json库或工具类，只能先手动拼接了
            return new HttpResponse("{"+result+"}");

        }
    }
}
