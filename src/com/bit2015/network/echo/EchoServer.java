package com.bit2015.network.echo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.print.attribute.standard.Severity;

public class EchoServer {
	private static final int PORT = 10003;
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			//1. 서버 소켓 생성
			serverSocket = new ServerSocket();
			
			//2. 바인딩
			InetAddress inetAddress = InetAddress.getLocalHost();
			String hostAddress = inetAddress.getHostAddress();
			serverSocket.bind( new InetSocketAddress( hostAddress, PORT ) );
			System.out.println( "[서버] 바인딩 " + hostAddress + ":" + PORT );
			
			
			//3. 연결 요청 대기 Blocking
			
			while(true){
				System.out.println("[서버]연결 기다림");
				Socket socket = serverSocket.accept();
				
				Thread thread = new EchoReciveThread(socket);
				thread.start();
			}
			
		} catch (IOException e){
			System.out.println("[서버] 에러 : " + e);
		} finally{
			if(serverSocket!=null&&serverSocket.isClosed() ==false){
		
			}
		}
	}

}
