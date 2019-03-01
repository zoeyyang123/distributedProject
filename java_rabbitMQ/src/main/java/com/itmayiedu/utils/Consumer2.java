package com.itmayiedu.utils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.swing.DefaultBoundedRangeModel;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class Consumer2 {
	private static final String QUEUE_NAME = "test_queue";

	public static void main(String[] args) throws IOException, TimeoutException {
		System.out.println("消费者....02");
		Connection connection = MQConnectionUtils.newConnection();

		final Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.basicQos(1);
		DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String msg = new String(body, "UTF-8");
				System.out.println("" + msg);
				try {

					Thread.sleep(500);
				} catch (Exception e) {
					// TODO: handle exception
				} finally {
					//手动通知MQ已经完成
					channel.basicAck(envelope.getDeliveryTag(), false);
				}

			}

		};

		channel.basicConsume(QUEUE_NAME, false, defaultConsumer);
		// channel.close();
		// connection.close();
	}
}
