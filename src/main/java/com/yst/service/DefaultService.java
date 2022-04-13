package com.yst.service;

import com.yst.entity.HttpResponse;

import java.util.HashMap;

/**
 * 404
 * @author Yan Siting
 */
public class DefaultService implements Service{


    /**
     * when resource does not exist , return this message
     *
     * @param request
     * @return
     */
    @Override
    public HttpResponse method(HashMap<String, Object> request) {
        return new HttpResponse("Resource is not exited");
    }
}
