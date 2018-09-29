package org.elsys.netprog.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class EchoServer 
{
	private static List<Socket> clientlist = new ArrayList<>();
	
	public static void main(String[] args) throws IOException 
	{
		ServerSocket serversock = null;
		try {
			serversock = new ServerSocket(9595);	
		    while(true) 
		    {
		    	Socket clientSocket = serversock.accept();
				clientlist.add(clientSocket);
		    	System.out.println("client connected from " + clientSocket.getInetAddress());
		    	
		    	Thread thread = new Thread(new Runnable() 
		    	{
					@Override
					public void run() 
					{
						try {
							BufferedReader in = new BufferedReader(
							new InputStreamReader(clientSocket.getInputStream()));
							String input = in.readLine();
							while (input != null) 
							{
								for (Socket client : clientlist) 
								{
									if(!client.equals(clientSocket)) 
									{
										System.out.println(input);
										PrintWriter out = new PrintWriter(client.getOutputStream(), true);
										out.println(input);
									}
								}
								input = in.readLine();
							}
						} catch (Throwable t) {
							System.out.println(t.getMessage());
						}
					}
				});
		    	thread.start();
		    }
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		} finally {
			if (!serversock.isClosed() && serversock != null) serversock.close();
			System.out.println("Server is closed");
		}
	}	
}