package com.pwhiting.tests.socket;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Main<S> implements Runnable {

	private ConsumerService<S> consumerService;
	private ProducerService<S> producerService;
	
	public Main() {
		Thread.currentThread().setName("Main");
		consumerService = new ConsumerService<>(4555, this::accept);
		producerService = new ProducerService<>("127.0.0.1", 4555);
	}
	
	public void run() {
		new Thread(consumerService, "consumer").start();
		new Thread(producerService, "producer").start();
	}
	
	private void accept(Object stream) {
		System.out.println("[" + Thread.currentThread().getName() + "] " + stream);
	}
	
	public void addSupplier(Supplier<S> supplier) {
		producerService.addSupplier(supplier);
	}
	
	public void addSupplier(S s) {
		producerService.addSupplier(s);
	}
	
	public static void main(String[] args) throws Exception {
		
		Main<String> main = new Main<String>();
		main.run();
		
		main.addSupplier(() -> "Book");
		main.addSupplier(() -> "Cupcake"); 
		main.addSupplier(() -> "Philip"); 
		
		IntStream.range(0, 5).forEach(i -> {
			main.addSupplier(genRandomString(10));
			sleep(1000);
		});
		
	}
	
	public static String genRandomString(int length) {
		return IntStream.range(0, length).boxed().map(i -> randChar()).reduce(String::concat).get();
	}
	
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {}
	}
	
	/**
	 * Returns random string as a single char
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String randChar() {
		return String.valueOf(new Random().nextInt(36) < 26 ? randAlpha() : randNum());
	}

	public static char randAlpha() {
		Random rand = new Random();
		return (char)(rand.nextBoolean() ? 'a' + rand.nextInt(26) : 'A' + rand.nextInt(26));
	}
	
	public static char randNum() {
		return (char)('0' + new Random().nextInt(10));
	}
}
