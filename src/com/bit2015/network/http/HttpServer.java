package com.bit2015.network.http;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.bit2015.network.chat.ChatServerProcessThread;

public class HttpServer {
	private static final int PORT = 8090;
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
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
			Thread thread = new HttpThread(socket);
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
		System.out.println("[HTTP] " + log);
	}

}
