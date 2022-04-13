package com.yst.server;

import com.yst.Util;
import com.yst.entity.HttpResponse;
import com.yst.entity.config.HttpServiceConfig;
import com.yst.service.Service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Yan Siting
 */
public class HttpService {

    /**
     * 如下一坨配置信息会从配置类加载过来
     */
    private int corePoolSize ;
    private int maximumPoolSize ;
    private long keepAliveTime ;
    private int blockingQueueLength ;

    public HttpService(HttpServiceConfig config){
        this.corePoolSize=config.getCorePoolSize();
        this.maximumPoolSize= config.getMaximumPoolSize();
        this.keepAliveTime=config.getKeepAliveTime();
        this.blockingQueueLength=config.getBlockingQueueLength();
    }


    /**
     * 启动服务线程池
     * @param port
     * @throws IOException
     */
    public void runService(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(this.corePoolSize,
                this.maximumPoolSize,
                this.keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(this.blockingQueueLength),
                r -> {
                    Thread t = new Thread(r);
                    t.setName("Thread--"+t.getId());
                    return t;
                });
        Socket socket;

        //一直在目标端口上监听tcp消息，收到消息则提交任务给线程池
        //不会一直循环，accept方法会阻塞
        while (true) {
            socket = serverSocket.accept();
            executor.execute(new SocketRunnable(socket));
        }
    }

    private static class SocketRunnable implements Runnable {
        private Socket socket;

        public SocketRunnable(Socket socket) {
            this.socket = socket;
        }

        /**
         * 核心任务
         */
        @Override
        public void run() {
            //加一个变量统计单次请求运行时间
            long l = System.currentTimeMillis();
            HttpResponse response = null;
            OutputStream out = null;
            try {
                InputStream is = socket.getInputStream();
                Reader reader = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(reader);
                out = socket.getOutputStream();

                //解析Request
                HashMap<String, Object> request = Util.analysisHttpRequest(br);
                Service service = UrlDispatcher.handleRequest(request);
                response = service.method(request);

                //代码确实有点丑陋，没时间搞解析和组装引擎了。手撸协议消息
                response.setStatusLine("HTTP/1.1 200 \r\n");
            } catch (Exception e) {
                System.out.print(new Date(System.currentTimeMillis())+"--服务内部报错："+e.toString()+" || ");
                response = new HttpResponse(e.toString());
                response.setStatusLine("HTTP/1.1 500 \r\n");
            } finally {
                response.setContentType("Content-Type: text/html;charset=UTF-8\r\n");
                System.out.println(Thread.currentThread().getName()+"-API调用耗时:"+((System.currentTimeMillis()-l)+"")+"ms");
                try {
                    if(Util.httpResponse(out,response)){
                        out.close();
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
