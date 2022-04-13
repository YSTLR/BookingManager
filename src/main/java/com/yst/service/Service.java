package com.yst.service;

import com.yst.entity.HttpResponse;

import java.util.HashMap;

/**
 * Service interface
 * @author Yan Siting
 */
public interface Service {

    /**
     * Standard method of service
     * @return
     */
    HttpResponse method(HashMap<String,Object> request);
}
