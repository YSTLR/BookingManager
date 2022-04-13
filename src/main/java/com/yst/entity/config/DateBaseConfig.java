package com.yst.entity.config;

/**
 * 初始数据库的配置类，没什么用了，直接使用参数类完成
 * @author Yan Siting
 */
public class DateBaseConfig {


    /**
     * 初始化房间个数
     */
    private int initRoomNum;

    /**
     * 初始化系统目标运行时间
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
