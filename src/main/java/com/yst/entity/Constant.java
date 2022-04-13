package com.yst.entity;

import com.sun.org.apache.xerces.internal.impl.dv.XSSimpleType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Yan Siting
 */
public class Constant {

    /**
     * 宇宙创世时间
     */
    public static final Date SYSTEM_BEGIN_DATE ;

    /**
     * 初始化房间数量
     */
    public static int INIT_ROOM_NUM = 50;

    /**
     * 宇宙毁灭时间
     */
    public static final int RUN_TIME = 365*20;

    static{
        Date date =null;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse("20220101");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SYSTEM_BEGIN_DATE=date;
    }
}
