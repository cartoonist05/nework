package com.bit2015.network.echo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
	
	private static final String SERVER_ADDERSS = "192.168.1.115";
	private static final int SERVER_PORT = 10003;
	public static void main(String[] args) {
		
		
		Socket socket = null;
		Scanner scanner = null;
		
		try{
			
		scanner = new Scanner(System.in);
		socket = new Socket();
		
		
		System.out.println("[클라이언트] 연결요청");
		socket.connect(new InetSocketAddress(SERVER_ADDERSS,SERVER_PORT));
		System.out.println("[클라이언트] 연결성공");
		
		
		OutputStream os = socket.getOutputStream();
		InputStream is = socket.getInputStream();
		
		
		while(true){
			System.out.print(">>");
			String data = scanner.nextLine();
			if("exit".equals(data) == true){
				break;
			}
			data += "\n";
			os.write(data.getBytes("UTF-8"));
			
			
			 byte[] buffer = new byte [128];
			 int readByteCount = is.read(buffer);
			 
			 data = new String(buffer,0,readByteCount,"UTF-8"); ////// 인코딩  byte--> String
			 System.out.print("<< "+data);
		}
		os.close();
		is.close();
		if(socket.isClosed() == false){
		socket.close();
		}
		
		} catch (IOException e){
			System.out.println("<< 에러 : " + e);
		}
		
		
	}

}