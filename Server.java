package server;

import java.net.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class Server extends JFrame {
	
	JTextArea chatBox = new JTextArea();
	
	public static void main(String args[]) {
		new Server();
	}
	
	public Server() {
		
		setLayout(new BorderLayout());
		chatBox.setEditable(false);
		add(new JScrollPane(chatBox), BorderLayout.CENTER);
		setTitle("Chat App");
		setSize(550, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		try {
			ServerSocket connectSocket = new ServerSocket(4000);
			chatBox.append("Server connected at: " + new Date());
			
			while(true) {
				Socket socket = connectSocket.accept();
				ClientThread clientThread = new ClientThread(socket);
				new Thread(clientThread).start();
				connectSocket.close();
			} 
		} catch (IOException exception) {
				System.out.println("Error" + exception);
			}
		
	}


public class ClientThread implements Runnable {
		Socket threadSocket;
		
		public ClientThread(Socket socket) {
			threadSocket = socket;
		}

		@Override
		public void run() {
			try {
				
				PrintWriter serverOutput = new PrintWriter(threadSocket.getOutputStream(), true);
				BufferedReader socketInput = new BufferedReader(new InputStreamReader(threadSocket.getInputStream()));
				
				serverOutput.println("You have connected at: " + new Date());
				chatBox.append("Client connected\n");
				
				while(true) {
					String chatInput = socketInput.readLine();
					chatBox.append(chatInput +"\n"); 
					System.out.println(chatInput);
				}
			} catch (IOException exception) {
				System.out.println("Error" + exception);
			}
			
		}
	
	}

}
