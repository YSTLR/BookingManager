package com.yst.entity.config;

/**
 * @author Yan Siting
 */
public class HttpServiceConfig {

    /**
     * http服务核心线程池大小
     */
    private int corePoolSize = 5;

    /**
     * http服务最大线程池大小
     */
    private int maximumPoolSize = 10;

    /**
     * http每次请求线程存活时间
     */
    private long keepAliveTime =5L;

    /**
     * 消息阻塞队列长度
     */
    private int blockingQueueLength = 100;

    /**
     * http服务监听的端口号
     */
    private int port = 9000;

    /**
     * 单例
     */
    private static HttpServiceConfig instance = null;

    private HttpServiceConfig(){

    }

    /**
     * 双检锁获取单例对象，保证线程安全
     * 备注：这个项目里没什么用，初始化只执行一次，不涉及线程安全问题
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
