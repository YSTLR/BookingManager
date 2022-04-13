package com.yst.entity.config;

/**
 * Config Class of Database Class，
 * @author Yan Siting
 */
public class DateBaseConfig {


    /**
     * initial number of rooms
     */
    private int initRoomNum;

    /**
     * initial system run time
     */
    private int initRunTime;

    public DateBaseConfig(int initRoomNum, int initRunTime) {
        this.initRoomNum = initRoomNum;
        this.initRunTime = initRunTime;
    }

    public DateBaseConfig() {
    }

    public int getInitRoomNum() {
        return initRoomNum;
    }

    public void setInitRoomNum(int initRoomNum) {
        this.initRoomNum = initRoomNum;
    }

    public int getInitRunTime() {
        return initRunTime;
    }

    public void setInitRunTime(int initRunTime) {
        this.initRunTime = initRunTime;
    }
}
