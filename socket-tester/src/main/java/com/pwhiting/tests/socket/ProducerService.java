package com.pwhiting.tests.socket;

import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Supplier;

/**
 * A client class that produces objects to be consumed by a server.
 * 
 * @author phwhitin
 *
 * @param <T>
 */
public class ProducerService<T> implements Runnable {

	private final String host;
	private final int port;
	
	private final Socket socket = new Socket();
	private ObjectOutputStream stream;
	
	private final ConcurrentLinkedQueue<Supplier<T>> taskQueue = new ConcurrentLinkedQueue<>();
	
	public ProducerService(String address, int port) {
		this.host = address;
		this.port = port;
	}
	
	@Override
	public void run() {
		
		try {
			socket.connect(new InetSocketAddress(host, port), 5000);
			stream = new ObjectOutputStream(socket.getOutputStream());
			
			while (true) {
				if (!taskQueue.isEmpty()) {
					T t = taskQueue.poll().get();
					System.out.println("[" + Thread.currentThread().getName() + "] " + t);
					stream.writeObject(t);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Could not connect to server");
		}
		
	}
	
	public void addSupplier(T v) {
		addSupplier(() -> v);
	}
	
	public void addSupplier(Supplier<T> supplier) {
		taskQueue.add(supplier);
	}
}
