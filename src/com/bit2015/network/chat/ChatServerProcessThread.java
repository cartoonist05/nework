package com.bit2015.network.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

public class ChatServerProcessThread extends Thread {
	private Socket socket ;
	private static final String PROTOCOL_DIVIDE = ":";
	private String nickname;
	private List<PrintWriter> listPrintWriters;
	
	
	public ChatServerProcessThread(Socket socket,List<PrintWriter> listPrintWriters){
		this.socket = socket;
		this.listPrintWriters = listPrintWriters;
	}
	public void run(){
		BufferedReader bufferedReader = null;
		PrintWriter printWriter =  null;
		
		try{
			//1. get Stream
			bufferedReader = 
					new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			printWriter = 
					new PrintWriter(socket.getOutputStream());
			
			//2. get remote host information
			InetSocketAddress inetSocketAddress = 
					(InetSocketAddress)socket.getRemoteSocketAddress();
			String remoteHostAddress = inetSocketAddress.getHostName();
			int remoteHostPort = inetSocketAddress.getPort();
			ChatServer.log("연결  됨  from " + remoteHostAddress + "   :   " + remoteHostPort);
			
			// 3. 요청 처리 
			while(true){
				String request = bufferedReader.readLine();
				if(request == null){
					ChatServer.log("클라이언트로 부터 연결 끊김");
					doQuit(printWriter);
					break;
				}
				
				String [] tokens = request.split(PROTOCOL_DIVIDE);
				if("join".equals(tokens[0])){
					doJoin(printWriter , tokens[1]);
				}else if("message".equals(tokens[0])){
					doMessage(tokens[1]);
				}else if("quit".equals(tokens[0])){
					doQuit(printWriter);
					break;
				}else{
					ChatServer.log("error : 알수없는 요청  : " +tokens[0] );
				}
			}
			// 자원정리
			
			bufferedReader.close();
			printWriter.close();
			if(socket.isClosed()== false){
				socket.close();
			}
			
		}catch(IOException e){
			ChatServer.log("error : " + e);
		}
	}
	private void doQuit(PrintWriter printWriter){
		//PrintWriter 제거
		removePrintWriter(printWriter);
		
		//퇴장 메세지 브로드 캐스팅	
		String data = nickname + "님이 퇴장했습니다.";
		broadcast(data);
	}
	private void doMessage(String message){
		String data = nickname + " : " + message;
		broadcast(data);
	}
	private void doJoin(PrintWriter printWriter, String nickname){
		//1. 닉네임 저장
		this.nickname = nickname;
		//2.
		String message = nickname + "님이 입장했습니다.";
		broadcast(message);
		//3. 
		addPrintWriter(printWriter);
		
		//4.
		printWriter.println("join : ok");
	}
	
	private void addPrintWriter(PrintWriter printWriter	){
		synchronized (listPrintWriters) {
			listPrintWriters.add(printWriter);
		}
	}
	private void removePrintWriter(PrintWriter printWriter){
		synchronized (listPrintWriters) {
			listPrintWriters.remove(printWriter);
		}
	}
	private void broadcast(String data){
		synchronized (listPrintWriters) {

//			for(PrintWriter printWriter : listPrintWriters){
//				printWriter = listPrintWriters.get(index);
//			}
			
			for (int i = 0; i < listPrintWriters.size(); i++) {
				PrintWriter printWriter = listPrintWriters.get(i);
				printWriter.println(data);
				printWriter.flush();
			}
		}
	}
}
