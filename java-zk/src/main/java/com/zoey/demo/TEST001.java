package com.zoey.demo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * TEST001 class
 * JAVA操作zk
 * 创建连接是一个新的线程，并且是守护线程，主线程结束子线程也结束。为了提高响应速度。
 * 如何保证在创建连接成功之后再进行其他操作？用countDownLatch.
 * @author zhaoyi
 * @date 18-7-24
 */
public class TEST001 {
    //zk连接地址
    private static final String CONNECT_STRING = "localhost:2181";
    //超时时间
    private static final int SESSION_TIME_OUT = 2000;

    public static final  CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

        ZooKeeper zk = new ZooKeeper(CONNECT_STRING, SESSION_TIME_OUT, new Watcher() {
            public void process(WatchedEvent event) {
                //获取事件状态
                KeeperState keeperState = event.getState();
                //判断为事件链接
                if (KeeperState.SyncConnected.equals(keeperState)){
                    EventType eventType = event.getType();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (EventType.None.equals(eventType)){
                        System.out.println("#####zk开始启动连接...######");
                    }
                    countDownLatch.countDown();
                }
            }
        });
        countDownLatch.await();
        //持久节点
        //String result = zk.create("/zktest_lasting3", "lasting".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        //临时节点，断开连接之后就会被删除
        String result = zk.create("/zktest_temp", "temp".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("######新增节点信息:"+result);
        Thread.sleep(10000);
        zk.close();

    }
}
