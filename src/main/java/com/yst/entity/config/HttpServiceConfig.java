package com.yst.entity.config;

/**
 * @author Yan Siting
 */
public class HttpServiceConfig {

    private int corePoolSize = 5;
    private int maximumPoolSize = 10;
    private long keepAliveTime =5L;
    private int blockingQueueLength = 100;
    private int port = 9000;
    private static HttpServiceConfig instance = null;

    private HttpServiceConfig(){

    }

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
