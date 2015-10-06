package com.bit2015.multithread;

public class MultithreadEx03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1 = new DigitThread();
		Thread t2 = new Thread(new AlphabetThread());
		
		t1.start();
		t2.start();
	}

}
