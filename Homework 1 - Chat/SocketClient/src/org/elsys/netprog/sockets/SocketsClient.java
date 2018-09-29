package org.elsys.netprog.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketsClient 
{
	public static void main(String[] args) throws IOException 
	{
		Socket clientsock = new Socket("localhost", 9595);
		
		try {
			Thread thread = new Thread(new Runnable() 
			{
				@Override
				public void run() {
					try {
						BufferedReader in = new BufferedReader(
					    new InputStreamReader(clientsock.getInputStream()));
						String input = in.readLine();
						while(input != null) 
						{
							System.out.println("server response: " + input);
							input = in.readLine();
						}
					} catch (Throwable t) {
						System.out.println(t.getMessage());
					}
				}
			});
		    thread.start();
		    
			try {
				PrintWriter out = new PrintWriter(clientsock.getOutputStream(), true);
				BufferedReader stdIn = new BufferedReader(
			    new InputStreamReader(System.in));
			    String input = stdIn.readLine();
			    while (input != null) 
			    {
			        out.println(input);
			        input = stdIn.readLine();
			    }
			} catch (Throwable t) {
				System.out.println(t.getMessage());
			}
			
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		} finally {
			if (!clientsock.isClosed() && clientsock != null) clientsock.close();
				System.out.println("Socket is closed");
		}
	}
}