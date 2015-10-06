package com.bit2015.network.chat;

import java.io.PrintWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
	private static final int PORT = 30000;
	
	public static void main(String[] args) {
		
		ServerSocket serverSocket = null;
		List<PrintWriter> listPrintWriters = new ArrayList<PrintWriter>();
		try{
			//1. 서버 소켓 생성
			serverSocket = new ServerSocket();
			
			//2. binding
			String hostAddress = InetAddress.getLocalHost().getHostAddress();
			serverSocket.bind(new InetSocketAddress(hostAddress,PORT));
			log("열결기다림   : " + hostAddress + " : " + PORT);
			
			//3. 연결 요청 대기
			while(true){
				
			Socket socket = serverSocket.accept();
			Thread thread = new ChatServerProcessThread(socket,listPrintWriters);
			thread.start();
			}
		}catch ( IOException e){
			log("error : " + e);
		} finally{
			if(serverSocket != null && serverSocket.isClosed()==false){
				try{
					serverSocket.close();
				} catch(IOException e){
					log("error : " + e);
				}
			}
		}
	}
	public static void log(String log){
		System.out.println("[chat-server] " + log);
	}
}
