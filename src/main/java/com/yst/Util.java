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
 * 工具类
 * @author Yan Siting
 */
public class Util {

    /**
     * 日期转换
     */
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    /**
     * 根据起止日期返回其中包含的所有日期
     * 备注：闭区间
     * @param startDate
     * @param endDate
     * @return
     */
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

    /**
     * 解析http请求，返回的map集合包含请求头的各项信息
     * @param reader
     * @return
     * @throws IOException
     */
    public static HashMap<String,Object> analysisHttpRequest(BufferedReader reader) throws Exception {

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
        }else {
            throw new Exception("不支持的请求方法");
        }
        return resultMap;
    }

    /**
     * 写响应信息
     * @param out
     * @param response
     * @return
     * @throws IOException
     */
    public static boolean httpResponse(OutputStream out, HttpResponse response) throws IOException {
        out.write(response.getStatusLine().getBytes(StandardCharsets.UTF_8));
        out.write(response.getContentType().getBytes(StandardCharsets.UTF_8));
        out.write("\r\n".getBytes(StandardCharsets.UTF_8));
        if(null!=response.getResponseBody()){
            out.write(response.getResponseBody().toString().getBytes(StandardCharsets.UTF_8));
        }
        out.flush();
        return true;
    }

}
