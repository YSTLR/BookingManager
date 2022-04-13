package com.yst;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Yan Siting
 */
public class Constant {

    public static final Date systemBeginDate ;

    static{
        Date date =null;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse("20220101");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        systemBeginDate=date;
    }
}
