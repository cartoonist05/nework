package com.bit2015.network.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class EchoReciveThread extends Thread {
	private Socket socket;
	
	public EchoReciveThread(Socket socket){
		this.socket = socket;
		
	}
	public void run(){

		// 4. 데이터 읽고/쓰기
		
		InetSocketAddress inetSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
			
		System.out.println( 
			"[서버] 연결됨 from " + 
			inetSocketAddress.getHostName() +
			":" +
			inetSocketAddress.getPort()
		);
//		InputStream is = null;
//		OutputStream os = null;
//		
		PrintWriter printWriter = null;
		BufferedReader br = null;
		
		
	try{
//		 is = socket.getInputStream();
//		 os = socket.getOutputStream();
		 br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		 
		 printWriter =new PrintWriter(socket.getOutputStream());
		while( true ) {
//			byte[] buffer = new byte[128];
//			int readByteCount = is.read( buffer );
//			if( readByteCount < 0 ) { // 클라이언트가 정상적으로 종료
//			
//				System.out.println( "[서버] 클라이언트로부터연결 끊김");
//				break;
//			}
//			
//			String data = new String( buffer, 0, readByteCount, "UTF-8" );
			String data = br.readLine();
			if(data==null){
				break;
			}
			System.out.println( "[서버] 데이터 수신:" + data );
			
//			os.write( data.getBytes( "UTF-8" ) );
//			os.flush();
			
			printWriter.println(data);
			printWriter.close();
		}
		// 스트림 닫기
		br.close();
//		is.close();
//		os.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
}
