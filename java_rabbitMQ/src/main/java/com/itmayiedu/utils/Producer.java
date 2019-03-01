package com.itmayiedu.utils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

//简单生产者
public class Producer {


	private static final String QUEUE_NAME = "test_queue";

	public static void main(String[] args) throws IOException, TimeoutException {

		//1创建连接
		Connection connection = MQConnectionUtils.newConnection();
		//2创建频道（amqp原理）
		Channel channel = connection.createChannel();
		//3创建一个队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.basicQos(1);
		for (int i = 1; i <= 50; i++) {
			//4创建消息
			String msg = "some_msg_" + i;
			//5生产者发送消息
			channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
		}
		channel.close();
		connection.close();
	}

}
