package ru.spbstu.telematics.parprog.lecture2;

public class RendezvousObject<T,R> {
	
	private T result = null;
	private R preset = null;
	T call(R r) {
		
		synchronized (this) {
			
			preset = r;
			this.notify();
			
			while (result == null) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	R calc (T t) {
		synchronized (this) {
			
			while (preset == null) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			result = t;
			this.notify();
		}
		return preset;
	}
}
