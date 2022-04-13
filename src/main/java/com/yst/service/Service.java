package com.yst.service;

import com.yst.entity.HttpResponse;

import java.util.HashMap;

/**
 * 服务统一接口
 * @author Yan Siting
 */
public interface Service {

    /**
     * 标准响应处理
     * @return
     */
    HttpResponse method(HashMap<String,Object> request);
}
