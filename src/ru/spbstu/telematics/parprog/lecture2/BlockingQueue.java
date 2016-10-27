package ru.spbstu.telematics.parprog.lecture2;

import java.util.ArrayList;

public class BlockingQueue<T> {
	
	private  ArrayList<T> storage = new ArrayList<T>();
	private final int maxSize;
	
	public BlockingQueue(int maxSize) {
		super();
		this.maxSize = maxSize;
	}

	void put(T t) {
		synchronized(storage) {
			while (storage.size() == maxSize) {
				//sleep
				try {
					storage.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
			
			storage.add(t);
			storage.notify();
 		}
	}
	
	T get(){
		T res = null;
		synchronized(storage) {
			while (storage.isEmpty()) {
				try {
					storage.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
			
			res = storage.remove(0);
			storage.notify();
 		}
		
		return res;
	}
}
