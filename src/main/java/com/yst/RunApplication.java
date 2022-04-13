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
     * support content-length request only , other type of request is not support
     * this http server only support post/put request , which request parameter in request body
     */
    private ServiceBak service = new ServiceBak();

    public static void main(String[] args) throws IOException {

        System.out.println("---Start Program---");

        //init database
        Database.build(new DateBaseConfig(Constant.INIT_ROOM_NUM,Constant.RUN_TIME));

        //starting http server
        new HttpService(HttpServiceConfig.getInstance()).runService(HttpServiceConfig.getInstance().getPort());

    }

}
