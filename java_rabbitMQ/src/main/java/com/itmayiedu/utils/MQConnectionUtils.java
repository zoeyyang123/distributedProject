package com.itmayiedu.utils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MQConnectionUtils {


	public static Connection newConnection() throws IOException, TimeoutException {

		ConnectionFactory factory = new ConnectionFactory();

		factory.setHost("127.0.0.1");

		factory.setUsername("zoey");

		factory.setPassword("123456");

		factory.setPort(5672);

		factory.setVirtualHost("/admin_host");
		Connection connection = factory.newConnection();
		return connection;
	}

}
