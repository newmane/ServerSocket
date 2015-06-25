import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Client {
	
	public static void main(String[] args) {
		new Client();
	}
	
	public Client() {
		
		Scanner scanner = new Scanner(System.in) ;
		try {
			Socket clientsocket = new Socket("192.168.0.8", 4000);
			PrintWriter output = new PrintWriter(clientsocket.getOutputStream(), true);
			BufferedReader input = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
			
			String inputstring = input.readLine();
			System.out.println(inputstring);
			
			while(true) {
				String userinput = scanner.nextLine();
				output.println(userinput);
			}
		}
		catch(IOException exception) {
			System.out.println("Error:" + exception);
		}
	}
}