package com.bit2015.network.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatClientReceiveThread extends Thread {

	private BufferedReader bufferedReader;
	public ChatClientReceiveThread( BufferedReader bufferedReader ) {
		this.bufferedReader = bufferedReader;
	}
	
	@Override
	public void run() {
		try {
			while( true ) {
				String data = bufferedReader.readLine();
				if( data == null ) {
					break;
				}

				System.out.println( data );
			}
		} catch( IOException ex ) {
			ChatClient.log( "error:" + ex );
		}
	}
}
