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
     * information in there will be set by HttpServiceConfig when this service is instantiated
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
     * server threadPool
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

        //listen tcp message in target port,and push it to threadPool
        //thread will BLOCKED because of  method accept()
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
         * core mission
         */
        @Override
        public void run() {
            //long l is used to calculate cost of time in one response
            long l = System.currentTimeMillis();
            HttpResponse response = null;
            OutputStream out = null;
            try {
                InputStream is = socket.getInputStream();
                Reader reader = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(reader);
                out = socket.getOutputStream();

                //analysis http request
                HashMap<String, Object> request = Util.analysisHttpRequest(br);
                Service service = UrlDispatcher.handleRequest(request);
                response = service.method(request);

                //emmmm,firstly I think web container is not allowed,so I choose analysis http request by myself....
                //this code is disgusting~~~~~LOLLOLLOLLOLLOL
                response.setStatusLine("HTTP/1.1 200 \r\n");
            } catch (Exception e) {
                System.out.print(new Date(System.currentTimeMillis())+"--error in server："+e.toString()+" || ");
                response = new HttpResponse(e.toString());
                response.setStatusLine("HTTP/1.1 500 \r\n");
            } finally {
                response.setContentType("Content-Type: text/html;charset=UTF-8\r\n");
                System.out.println(Thread.currentThread().getName()+"-API response time:"+((System.currentTimeMillis()-l)+"")+"ms");
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
