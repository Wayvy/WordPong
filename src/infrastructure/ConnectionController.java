package infrastructure;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ConnectionController extends Thread {
	//private InputStream inputStream;
	//private Scanner reader;
	//private OutputStream putputStream;
	//private PrintWriter writer;
	private Socket nemesis;
	private int port;
	private String address;

	/**
	 * The Connection controller handles the hosting and the joining of one player to another.
	 * The methods hostGame and joinGame create new Runnable's, that will run in the background 
	 * and the check the connection to the other player.
	 * Also the start of the game should be initiated in here.
	 */
	public ConnectionController(int port) {
		this.port = port;
	}
	
	public ConnectionController(int port, String address) {
		this.address = address;
		this.port = port;
	}

	/**
	 * Host the game and lets other people connect to this Socket.
	 * Runs in the background, and checks the connection and creates the 
	 * GameThread, once the connection to the other player has been accepted.
	 */
	public void hostGame() {
		System.out.println("It hosts");
		Thread hostThread = new Thread() {

//			@Override
			public void run() {
				try {
					System.out.print("Host Game");
					ServerSocket host = new ServerSocket(port, 100);
					nemesis = host.accept();
					OutputStream putputStream = nemesis.getOutputStream();
					PrintWriter writer = new PrintWriter(putputStream);
					writer.println("Hello i Am Host");
					writer.flush();
					InputStream inputStream = nemesis.getInputStream();
					Scanner reader = new Scanner(inputStream);
					String message = reader.nextLine();
					System.out.println(message);
				} catch (IOException e) {
					// TODO Maybe the client has to resends the dataStreams
					e.printStackTrace();
				}
			};
			

		};
		
		hostThread.start();
		

	}

	/**
	 * Joining an already existing host 
	 */
	public void joinGame() {
		Thread joinThread = new Thread() {

//			@Override
			public void run() {

				try {
					System.out.println(port);
					nemesis = new Socket(InetAddress.getByName(address), port);
					System.out.println(nemesis);
					
					//Erhalte Nachricht
					InputStream inputStr = nemesis.getInputStream();
					Scanner input = new Scanner(inputStr);
					OutputStream putputStream = nemesis.getOutputStream();
					PrintWriter writer = new PrintWriter(putputStream);
					
					String message = input.nextLine();
					System.out.println("RECEIVED MESSAGE: " + message);
					
					//String message = reader.nextLine();
					System.out.println("Message: " + message);

					
					
					writer.write("Hello I am Client");
					writer.flush();
					
					
					input.close();
					writer.close();
					nemesis.close();
					
				} catch (IOException e) {
					// TODO Something with the IOStreams is wrong, how can that
					// be solved??
					e.printStackTrace();
				}

			}
		};
		
		joinThread.start();
	}
}
