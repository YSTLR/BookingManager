package com.yst.entity.config;

/**
 * @author Yan Siting
 */
public class DateBaseConfig {


    private int initRoomNum;

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
