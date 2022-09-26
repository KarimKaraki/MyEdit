package javaapplication1;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.WindowConstants;


public class Server 
{
	static Vector<DataOutputStream> userlist = new Vector<DataOutputStream>(10, 1);
	
	public Server() throws IOException {
		System.out.println("Server Started!");
		ServerSocket serverSocket = new ServerSocket(5522);
		while (true)
		{
                        //Socket clientSocket= serverSocket.accept()
			Socket clientSocket = serverSocket.accept();/////////////////////////
			System.out.println("connection established");
			new clientHandler(clientSocket).start();        
		}
		
	}

	public static void SendNotification(String message){
		for(DataOutputStream user:userlist) {
				try {
					user.writeUTF("SERVER: "+message);
				} catch (IOException e) {
					e.printStackTrace();
				}							
			}
		}
		
	
	
	static class clientHandler extends Thread
	{
		Socket clientSocket;
		//BufferedReader inFromClient;
		DataInputStream inFromClient;
		DataOutputStream outToClient;
		String name;
        


		public clientHandler(Socket clientSocket) {
			this.clientSocket = clientSocket;
                        //this.name=clientSocket.username;
		}
		@Override
		public void run()
		{
			try {
				//inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				inFromClient=new DataInputStream(clientSocket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				outToClient = new DataOutputStream(clientSocket.getOutputStream());
				//outToClient.writeBytes("TEST");
				userlist.add(outToClient);
			} catch (IOException e) {

				e.printStackTrace();
			} 

                        
			while (true) 
			{			
				String s = "";
				try {
					 //s = inFromClient.readLine();
					s=inFromClient.readUTF();
				} catch (IOException e) {
					
					e.printStackTrace();
					try {
                                                SendNotification(name +" has exited the Group Chat!!");
						outToClient.close();
						inFromClient.close();
						clientSocket.close();
						userlist.removeElement(outToClient);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				}
				//System.out.println("TEST");
				if (s != null) {
//					if (s.startsWith("@")) {
//						
//						
//					}

						for(DataOutputStream user:userlist) {
							if (user!=this.outToClient) {
								try {
									//user.writeBytes(this.name + ":" + s + '\n');
									//user.writeBytes(s+'\n');
									user.writeUTF(s);
								} catch (IOException e) {
									e.printStackTrace();
								}							
							}
						}
					}
				}
			}
		}
	}
