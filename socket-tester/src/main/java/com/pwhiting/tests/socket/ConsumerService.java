package com.pwhiting.tests.socket;

import java.io.EOFException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class ConsumerService<S> implements Runnable {

	private final int port;
	private final Consumer<S> consumer;
	
	private ObjectInputStream stream;
	
	public ConsumerService(int port, Consumer<S> consumer) {
		this.port = port;
		this.consumer = consumer;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		
		try (ServerSocket echoSock = new ServerSocket(port); Socket client = echoSock.accept())	{
			
			stream = new ObjectInputStream(client.getInputStream());
			while (true) {
				try {
					S obj = (S) stream.readObject();
					consumer.accept(obj);
				} catch (EOFException e) {}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}

}
