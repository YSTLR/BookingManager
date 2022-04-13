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
     * date of the system
     */
    public static final Date SYSTEM_BEGIN_DATE ;

    /**
     * initial number of rooms
     */
    public static int INIT_ROOM_NUM = 50;

    /**
     * maximum days,influence the size of database
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
