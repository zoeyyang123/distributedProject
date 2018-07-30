package com.zoey.server;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * ZkServerClient class
 * 模拟会话的客户端，当服务端信息变动时，会先清掉serverlist，然后重新添加进list
 * 采用轮循机制负载均衡
 * @author zhaoyi
 * @date 18-7-30
 */
public class ZkServerClient {
    public static List<String> listServer = new ArrayList<String>();

    public static void main(String[] args) {
        initServer();
        ZkServerClient client = new ZkServerClient();
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String name;
            try {
                name = console.readLine();
                if ("exit".equals(name)) {
                    System.exit(0);
                }
                client.send(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 注册所有server
    public static void initServer() {
        String path = "/zktest";
        final ZkClient zkClient = new ZkClient("127.0.0.1:2181", 6000, 1000);
        List<String> children = zkClient.getChildren(path);
        for (String ipServer : children) {
            listServer.add((String) zkClient.readData(path + "/" + ipServer));
        }
        System.out.println("####从注册中心获取服务信息####listServer:" + listServer.toString());
        // 监听事件
        zkClient.subscribeChildChanges(path, new IZkChildListener() {

            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                listServer.clear();
                for (String ctPath : currentChilds) {
                    listServer.add((String) zkClient.readData(parentPath + "/" + ctPath));
                }
                System.out.println("####节点事件监听发生变化### listServer:" + listServer.toString());

            }
        });
        // listServer.clear();
        // listServer.add("127.0.0.1:8080");
    }

    private static int reqestCount = 0;

    // 获取当前server信息
    public static String getServer() {
        int serverCount = listServer.size();
        String serverHost = listServer.get(reqestCount % serverCount);
        reqestCount++;
        return serverHost;
    }

    public void send(String name) {

        String server = ZkServerClient.getServer();
        String[] cfg = server.split(":");

        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket(cfg[0], Integer.parseInt(cfg[1]));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println(name);
            while (true) {
                String resp = in.readLine();
                if (resp == null)
                    break;
                else if (resp.length() > 0) {
                    System.out.println("Receive : " + resp);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

