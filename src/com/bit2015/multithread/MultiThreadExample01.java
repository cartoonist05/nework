package com.bit2015.multithread;

public class MultiThreadExample01 {

	public static void main(String[] args) {
		
		Thread t1 = new DigitThread();
		Thread t2 = new AlphabetThread();
		Thread t3 = new DigitThread();
		t1.start();
		t2.start();
		t3.start();
		for (char c = 'A'; c <= 'Z'; c++) {
			System.out.print(c);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
	}
}
