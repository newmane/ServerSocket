import java.net.*;
import java.util.*;
import java.io.*;

public class Server {
	
	public static void main(String args[]) {
		new Server();
	}
	
	public Server() {
		
		try {
			ServerSocket connectSocket = new ServerSocket(4000);
			System.out.println("Server connected at: " + new Date());
			
			while(true) {
				Socket socket = connectSocket.accept();
				ClientThread clientThread = new ClientThread(socket);
				new Thread(clientThread).start();
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
				
				while(true) {
					String chatInput = socketInput.readLine();
					System.out.println(chatInput);
				}
			} catch (IOException exception) {
				System.out.println("Error" + exception);
			}
			
		}
	
	}

}
