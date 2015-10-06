package com.bit2015.network.echo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPEchoServer {
	private static final int PORT = 60001;
	private static final int BUFFER_SIZE = 1024;
	public static void main(String[] args) {
		DatagramSocket dataGramSocket = null;
		
		
		try{
			//1. UDP 서버 소켓 생성
			dataGramSocket = new DatagramSocket(PORT);
			
			//2. 수신대기
			log("packet 수신 대기");
			DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE],BUFFER_SIZE);
			dataGramSocket.receive(receivePacket);
			
			//3. 수신데이타 출력
			String message = new String(receivePacket.getData(),0,receivePacket.getLength(),"UTF-8");
			log("Packet 수신 : " + message);
			
			//4. 데이터 보내기
			DatagramPacket sendPacket =
					new DatagramPacket(
							receivePacket.getData(),
							receivePacket.getLength(),
							receivePacket.getAddress(),
							receivePacket.getPort());
			dataGramSocket.send(sendPacket);
			
			//5.자원정리
			dataGramSocket.close();
		} catch(IOException e){
			log("error : " + e);
		}
	}
	public static void log(String log){
		System.out.println("[UDP-Echo-Server]"+log);
	}

}
