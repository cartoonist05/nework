package com.bit2015.network.time;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Scanner;

public class TimeClient {
	private static String SERVER_IP = "192.168.1.115";
	private static int SERVER_PORT =60005;
	private static int BUFFER_SIZE = 1024;
	public static void main(String[] args) {
		DatagramSocket dataGramSocket = null;
//		Scanner scanner = new Scanner(System.in);
		try{
			while(true){
				//1.UDP Client Socket 생성
				dataGramSocket = new DatagramSocket();
			
				//2. packet 보내기

				String message = "";

				byte[] data = message.getBytes();
				
				DatagramPacket sendPacket = new	DatagramPacket(data, data.length,new InetSocketAddress(SERVER_IP,SERVER_PORT));
				
				dataGramSocket.send(sendPacket);
			 
				//3. 데이터 받기
				DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
				dataGramSocket.receive(receivePacket);
				
				//4. 데이터 출력
				message = 
						new String(receivePacket.getData(),0,receivePacket.getLength(),"UTF-8");
				System.out.println(message);
				System.out.println("");
			}
			
		} catch (SocketException e){
			log("error : " + e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			//5. 자원정리
			dataGramSocket.close();
		}
	}
	public static void log(String log){
		System.out.println("[Time-Client]"+log);
	}

}		
		
