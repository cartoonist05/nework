package com.bit2015.multithread;

public class MultithreadEx04 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1 = new DigitThread();
		Thread t2 = new Thread(new AlphabetRunnableImpl2());
		
		t1.start();
		t2.start();
	}

}
