package com.bit2015.network.time;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeServer {
	private static final int PORT = 60005;
	private static final int BUFFER_SIZE = 1024;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss a" );
		String data = format.format( new Date() );
		
		DatagramSocket dataGramSocket = null;
		
		try {
			//1. UDP 서버 소켓 생성
			dataGramSocket = new DatagramSocket(PORT);
			
			//2. 수신대기
			log("Packet 수신대기");
			DatagramPacket receivePacket = 
					new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
			dataGramSocket.receive(receivePacket);
			
			
			//3. data 값 넣어주기
			
			receivePacket.setData(data.getBytes());
			
			//4. 수신데이타 출력
			String message = new String(receivePacket.getData(),
					0,
					receivePacket.getLength(),
					"UTF-8");
			
			log("Packet 수신 : " + message);
			//5. 데이터 보내기
			DatagramPacket sendPacket =
					new DatagramPacket(
							receivePacket.getData(),
							receivePacket.getLength(),
							receivePacket.getAddress(),
							receivePacket.getPort());
			dataGramSocket.send(sendPacket);
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			//5.자원정리
			dataGramSocket.close();
		}
		
		
	

	}
	public static void log(String log){
		System.out.println("[Time-Server]"+log);
	}
}
