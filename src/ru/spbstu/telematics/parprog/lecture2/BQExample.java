package ru.spbstu.telematics.parprog.lecture2;

import java.util.Random;

public class BQExample {
	public static void main(String[] args) throws InterruptedException {
		
		BlockingQueue<Integer> bq = new BlockingQueue<>(3);
		Runnable r1 = new Runnable() {
			private Random r = new Random();
			@Override
			public void run() {
				while (!Thread.interrupted()) {
					int num = r.nextInt(100);
					System.out.println(Thread.currentThread().getName() + " generated " + num);
					bq.put(num);
					try {
						Thread.sleep(num);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		};
		Runnable r2 = new Runnable() {
			
			@Override
			public void run() {
				while (!Thread.interrupted()) {
					System.out.println(Thread.currentThread().getName() + " read " + bq.get());
					try {
						Thread.sleep(new Random().nextInt(1000));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		};
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t2.start();
		t1.start();
		t1.join();
		t2.join();
		
		
	}
}
