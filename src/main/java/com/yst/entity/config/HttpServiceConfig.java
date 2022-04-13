package com.yst.entity.config;

/**
 * @author Yan Siting
 */
public class HttpServiceConfig {

    /**
     * core size of http-server's threadPool
     */
    private int corePoolSize = 5;

    /**
     * maximum of http-server's threadPool
     */
    private int maximumPoolSize = 10;

    /**
     * keepAliveTime of http-server's threadPool
     */
    private long keepAliveTime =5L;

    /**
     * length of queue in threadPool
     */
    private int blockingQueueLength = 100;

    /**
     * http-server's listening port
     */
    private int port = 9000;

    /**
     * singleton
     */
    private static HttpServiceConfig instance = null;

    private HttpServiceConfig(){

    }

    /**
     * DCL,ensure thread safe
     * P.S.：In this program,DCL is useless,because jvm only use this instance once
     * @return
     */
    public static HttpServiceConfig getInstance(){
        if(null==instance){
            synchronized (HttpServiceConfig.class){
                if(null==instance){
                    instance = new HttpServiceConfig();
                }
            }
        }
        return instance;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public int getBlockingQueueLength() {
        return blockingQueueLength;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setBlockingQueueLength(int blockingQueueLength) {
        this.blockingQueueLength = blockingQueueLength;
    }
}
