package ru.spbstu.telematics.parprog.lecture2;

public class ThreadsStuff {
	
	private final static int SUM = 1; 
	private final static Object LOCK = new Object();
	static class Store {
		
		int account = 0;

		public  int getAccount() {
			return account;
		}

		public  void setAccount(int account) {
			this.account = account;
		}
		
		  void transferGoods(Buyer b){ 
			b.setCount(b.getCount() + 1);
		}
	}
	
	static class Buyer {
		int account = 10000000;
		int count = 0;
		public   int getAccount() {
			return account;
		}
		public   void setAccount(int account) {
			this.account = account;
		}
		public   int getCount() {
			return count;
		}
		public   void setCount(int count) {
			this.count = count;
		}
	}
	
	static class MarketProcess implements Runnable {

		Buyer buyer;
		Store store;
		
		public MarketProcess(Buyer buyer, Store store) {
			super();
			this.buyer = buyer;
			this.store = store;
		}

		@Override
		public void run() {
			while(!Thread.currentThread().isInterrupted()) {
				synchronized (LOCK) {
					if(buyer.getAccount() >= SUM) {
						store.setAccount(store.getAccount() + SUM);
						buyer.setAccount(buyer.getAccount() - SUM);
						store.transferGoods(buyer);
					} else {
						break;
					}
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		Store s = new Store();
		Buyer a = new Buyer();
		Buyer b = new Buyer();
		
		Thread t1 = new Thread(new MarketProcess(a, s));
		Thread t2 = new Thread(new MarketProcess(b, s));
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println(s.getAccount() + a.getAccount() + b.getAccount());
		
	}
}
