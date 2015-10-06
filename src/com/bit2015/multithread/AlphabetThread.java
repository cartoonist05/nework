package com.bit2015.multithread;

public class AlphabetThread extends Thread {

	@Override
	public void run() {
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
