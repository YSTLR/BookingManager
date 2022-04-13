package com.yst.server;

import com.yst.Util;
import com.yst.entity.HttpResponse;
import com.yst.entity.config.HttpServiceConfig;
import com.yst.service.Service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Yan Siting
 */
public class HttpService {

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

        @Override
        public void run() {
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
                System.out.println("---"+request);
                response = service.method(request);
                response.setStatusLine("HTTP/1.1 200 \r\n");
            } catch (Exception e) {
                response.setStatusLine("HTTP/1.1 500 \r\n");
            } finally {
                response.setContentType("Content-Type: text/html;charset=UTF-8\r\n");
                try {
                    Util.httpResponse(out,response);
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
