package com.yst;

import com.yst.entity.Constant;
import com.yst.entity.Database;
import com.yst.entity.config.DateBaseConfig;
import com.yst.server.HttpService;
import com.yst.entity.config.HttpServiceConfig;
import com.yst.service.ServiceBak;

import java.io.IOException;

/**
 * @author Yan Siting
 */
public class RunApplication {

    /**
     *备注：只支持content-length模式的传参，不支持其他方式
     *     时间来不及了，目前http服务器只支持post和put请求，参数在请求体里
     *     返回的json格式是手动拼接的，时间关系没有写通用方法
     */
    private ServiceBak service = new ServiceBak();

    public static void main(String[] args) throws IOException {

        System.out.println("---程序开始启动---");

        //初始化数据库
        Database.build(new DateBaseConfig(Constant.INIT_ROOM_NUM,Constant.RUN_TIME));

        //启动http服务器
        new HttpService(HttpServiceConfig.getInstance()).runService(HttpServiceConfig.getInstance().getPort());

    }

}
