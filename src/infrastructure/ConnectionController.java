package infrastructure;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import javax.swing.JOptionPane;

/**
 * Handles connecting every request to join a server or host one.
 * @author Wavy
 * @version 0.7
 */

public class ConnectionController extends Thread {
	private InputStream inputStream;
	private Scanner reader;
	private OutputStream putputStream;
	private PrintWriter writer;
	private Socket nemesis;
	private int port;
	private String address;
	private String nameHost;
	private String nameClient;

	/**
	 * The Connection controller handles the hosting and the joining of one
	 * player to another. The methods hostGame and joinGame create new
	 * Runnable's, that will run in the background and the check the connection
	 * to the other player. Also the start of the game should be initiated in
	 * here.
	 */
	public ConnectionController(int port) {
		this.port = port;
	}

	public ConnectionController(int port, String address) {
		this.address = address;
		this.port = port;
	}

	/**
	 * Host the game and lets other people connect to this Socket. Runs in the
	 * background, and checks the connection and creates the GameThread, once
	 * the connection to the other player has been accepted.
	 */
	public void hostGame() {
		System.out.println("It hosts");
		Thread hostThread = new Thread() {

			// @Override
			public void run() {
				try {
					System.out.print("Host Game");
					ServerSocket host = new ServerSocket(port, 100);
					nameHost = JOptionPane.showInputDialog("Please enter your name");
					nemesis = host.accept();
					
					

					setupFileIO();
					
					writer.println("Hello, " + nameHost + " !");
					writer.flush();
					
					while(true)
					{
						if(Thread.interrupted())
							break;
					}
					host.close();
				} catch (IOException e) {
					// TODO Maybe the client has to resends the dataStreams
					e.printStackTrace();
				} finally {
					reader.close();
					writer.close();
					
					try {
						nemesis.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		hostThread.start();

	};

	/**
	 * Joining an already existing host
	 */
	public void joinGame() {
		System.out.println("It joins");
		Thread joinThread = new Thread() {

			// @Override
			public void run() {

				try {
					nameClient = JOptionPane.showInputDialog("Please enter your name");
					System.out.println(port);
					nemesis = new Socket(InetAddress.getByName(address), port);
					System.out.println(nemesis);
					
					setupFileIO();
					
					writer.println("Hello, " + nameClient + " !");
					writer.flush();
					
					String message = reader.nextLine();
					System.out.println(message);
					
					Scanner keyBoardScn = new Scanner(System.in);
					message = keyBoardScn.nextLine();
					writer.write(message);
					writer.flush();
					keyBoardScn.close();
					message = reader.nextLine();
					System.out.println(message);
					reader.close();
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
	
	private void setupFileIO()
	{
		try {
			// Setup receiving Messages
			inputStream = nemesis.getInputStream();
			reader = new Scanner(inputStream);

			// Setup sending Messages
			putputStream = nemesis.getOutputStream();
			writer = new PrintWriter(putputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
}
