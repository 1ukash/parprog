package ru.spbstu.telematics.parprog.lecture2;

public class ThreadsAreWaiting {
	public static void main(String[] args) throws InterruptedException {
		
		final Object lock = new Object();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				long time1 = System.currentTimeMillis();
				System.out.println(time1);
				synchronized (lock) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				System.out.println(time1 - System.currentTimeMillis());
			}
		});
		t1.start();
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
					synchronized (lock) {
						lock.notify();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t2.start();
		t1.join();
		t2.join();
		
	}
}
