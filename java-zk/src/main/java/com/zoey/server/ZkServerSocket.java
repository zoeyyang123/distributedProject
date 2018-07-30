package com.zoey.server;

import org.I0Itec.zkclient.ZkClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ZkServerScoekt class
 * 模拟会话的服务端，更改port值，可以多开服务，并注册到本地zookeeper中，
 * 临时节点，连接超时时间为10s。
 * @author zhaoyi
 * @date 18-7-30
 */
public class ZkServerSocket implements Runnable {
    private static int port = 18082;

    public static void main(String[] args) throws IOException {
        ZkServerSocket server = new ZkServerSocket(port);
        Thread thread = new Thread(server);
        thread.start();
    }

    public ZkServerSocket(int port) {
        this.port = port;
    }

    public void regServer() {
        // 向ZooKeeper注册当前服务器
        ZkClient client = new ZkClient("127.0.0.1:2181", 60000, 1000);
        String path = "/zktest/server" + port;
        if (client.exists(path))
            client.delete(path);
        client.createEphemeral(path, "127.0.0.1:" + port);
    }

    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            regServer();
            System.out.println("Server start port:" + port);
            Socket socket = null;
            while (true) {
                socket = serverSocket.accept();
                new Thread(new ServerHandler(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (Exception e2) {

            }
        }
    }


}

