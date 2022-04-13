package com.yst.service;

import com.yst.entity.HttpResponse;

import java.util.HashMap;

/**
 * 模拟404
 * @author Yan Siting
 */
public class DefaultService implements Service{


    /**
     * 访问不存在的路径时的处理逻辑
     *
     * @param request
     * @return
     */
    @Override
    public HttpResponse method(HashMap<String, Object> request) {
        return new HttpResponse("访问的资源不存在");
    }
}
