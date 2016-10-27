package ru.spbstu.telematics.parprog.lecture2;

import java.util.Random;

public class RZExample {
	public static void main(String[] args) throws InterruptedException {
		RendezvousObject<String, Integer> rz = new RendezvousObject<>();
		Runnable r1 = new Runnable() {
			private Random r = new Random();

			@Override
			public void run() {
				int num = r.nextInt(100);
				System.out.println(Thread.currentThread().getName() + " generated " + num);
				try {
					Thread.sleep(num);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				String res = rz.call(num);
				System.out.println("got " + res  +" from another thread");
			}
		};
		Runnable r2 = new Runnable() {

			@Override
			public void run() {
					try {
						Thread.sleep(new Random().nextInt(1000));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String s = "test";
					Integer num = rz.calc(s);
					
					System.out.println("Result is " + s + num);
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
