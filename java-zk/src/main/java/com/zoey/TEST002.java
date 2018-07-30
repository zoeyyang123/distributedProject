package com.zoey;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * TEST002 class
 * 封装zk连接,包括创建连接，创建监听，创建节点，更新节点，开启通知
 * @author zhaoyi
 * @date 18-7-24
 */
public class TEST002 implements Watcher {

    //zk连接地址
    private static final String CONNECT_STRING = "localhost:2181";
    //超时时间
    private static final int SESSION_TIME_OUT = 2000;

    public static final CountDownLatch countDownLatch = new CountDownLatch(1);
    ZooKeeper zk;

    /**
     * 创建连接
     * @param connectString
     * @param sessionTimeout
     */
    public void createConnection(String connectString, int sessionTimeout){
        try {
            zk = new ZooKeeper(connectString,sessionTimeout,this);
            countDownLatch.countDown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建持久化节点
     * @param path
     * @param data
     * @return
     */
    public boolean createNode(String path, String data){
        try {
            exists(path,true);
            String result = zk.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("######新增节点成功,path:"+ path+",data:"+data);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateNode(String path, String data){
        try {
            zk.setData(path, data.getBytes(),-1);
            System.out.println("######修改节点成功,path:"+ path+",data:"+data);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 创建监听
     * @param watchedEvent
     */
    public void process(WatchedEvent watchedEvent) {
        System.out.println();
        System.out.println("#########事件通知开始#########");
        //获取事件状态
        Event.KeeperState keeperState = watchedEvent.getState();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //判断为事件链接
        if (Event.KeeperState.SyncConnected.equals(keeperState)){
            String path = watchedEvent.getPath();
            Event.EventType eventType = watchedEvent.getType();
            System.out.println("#####进入process方法###keeperState:"+keeperState+",path:"+ path+",eventType:"+eventType);

            if (Event.EventType.None.equals(eventType)){
                System.out.println("#####zk开始启动连接...########");
            }else if (Event.EventType.NodeCreated.equals(eventType)){
                System.out.println("获取事件通知，新增节点node:"+path+"");
            }else if (Event.EventType.NodeDataChanged.equals(eventType)){
                System.out.println("获取事件通知，当前节点node:"+path+"，被修改");
            }

        }
        System.out.println("#########事件通知结束#########");
        System.out.println();
    }

    /**
     * 开启监听
     * @param path
     * @param isWatched
     * @return
     */
    public Stat exists(String path, boolean isWatched){
        try {
            return zk.exists(path,isWatched);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 关闭连接
     */
    public void close() {
        try {
            if (zk!=null){
                zk.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TEST002 test002 = new TEST002();
        test002.createConnection(CONNECT_STRING,SESSION_TIME_OUT);
        //必须现有父节点，才能创建子节点，无法创建两层，需要自行封装
        test002.createNode("/zktest/0013","002");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test002.updateNode("/zktest/0013","003");
        test002.close();
    }
}
