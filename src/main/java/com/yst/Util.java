package com.yst;

import com.yst.entity.Database;
import com.yst.entity.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Yan Siting
 */
public class Util {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    public static String[] getDateRange(String startDate,String endDate){
        System.out.println(startDate);
        System.out.println(endDate);
        Date start = null;
        Date end = null;
        try {
            start = sdf.parse(startDate);
            end = sdf.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        long days =(end.getTime() - start.getTime())/(1000*60*60*24);
        String[] result = new String[(int)days+1];
        for(int i=0 ; i<=days ; i++){
            result[i] = sdf.format(new Date(start.getTime()+1000 * 60 * 60 * 24 *i));
        }
        return result;
    }

    public static void main(String[] args) {
        String[] dateRange = getDateRange("20220401", "20220403");
        for(String str:dateRange){
            System.out.println(str);
        }

    }

    public static HashMap<String,Object> analysisHttpRequest(BufferedReader reader) throws IOException {

        String line;
        HashMap<String,Object> resultMap= new HashMap();

        //解析http响应报文状态行信息
        String httpStatusLine = reader.readLine();
        String[] headerParams = httpStatusLine.split(" ");
        resultMap.put("method",headerParams[0]);
        resultMap.put("url",headerParams[1]);
        resultMap.put("protocol",headerParams[2]);


        //解析响应头信息
        while ((line = reader.readLine()) != null && line.trim().length()!=0) {
            String[] strs = line.split(":");
            resultMap.put(strs[0].trim(),strs[1].trim());
        }

        //解析请求体
        //备注：时间关系，这里自定义的http服务器只支持Content-Length头，不支持chunked模式和短连接模式
        if("PUT".equals(resultMap.get("method"))||"POST".equals(resultMap.get("method"))){
            int contentLength = resultMap.containsKey("Content-Length")?Integer.parseInt((String) resultMap.get("Content-Length")):5000;
            char[] inputBuffer = new char[contentLength];
            int i = 0 ;
            while(i<contentLength){
                inputBuffer[i] = (char)reader.read();
                i++;
            }
            HashMap<String,String> requestBody = new HashMap<>();
            String params = new String(inputBuffer).substring(1);
            params=params.substring(0,params.length()-1);
            String[] paramsArray =params.split(";");
            for(String str:paramsArray){
                requestBody.put(str.split(":")[0].trim(),str.split(":")[1].trim());
            }
            resultMap.put("RequestBody",requestBody);
        }
        return resultMap;
    }

    public static void httpResponse(OutputStream out, HttpResponse response) throws IOException {
        out.write(response.getStatusLine().getBytes(StandardCharsets.UTF_8));
        out.write(response.getContentType().getBytes(StandardCharsets.UTF_8));
        out.write("\r\n".getBytes(StandardCharsets.UTF_8));
        out.write(response.getResponseBody().toString().getBytes(StandardCharsets.UTF_8));


        out.flush();
    }

}
