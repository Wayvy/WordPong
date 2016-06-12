package infrastructure;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ConnectionController extends Thread {
	private InputStream inputStream;
	private Scanner reader;
	private OutputStream putputStream;
	private PrintWriter writer;
	private Socket nemesis;

	/**
	 * The Connection controller handles the hosting and the joining of one player to another.
	 * The methods hostGame and joinGame create new Runnable's, that will run in the background 
	 * and the check the connection to the other player.
	 * Also the start of the game should be initiated in here.
	 */
	public ConnectionController() {
	}

	/**
	 * Host the game and lets other people connect to this Socket.
	 * Runs in the background, and checks the connection and creates the 
	 * GameThread, once the connection to the other player has been accepted.
	 */
	public void hostGame() {
		System.out.println("It hosts");
		new Runnable() {

//			@Override
			public void run() {
				try {
					System.out.print("Host Game");
					ServerSocket host = new ServerSocket(11200, 100);
					nemesis = host.accept();
					inputStream = nemesis.getInputStream();
					reader = new Scanner(inputStream);
					putputStream = nemesis.getOutputStream();
					writer = new PrintWriter(putputStream);
				} catch (IOException e) {
					// TODO Maybe the client has to resends the dataStreams
					e.printStackTrace();
				}
				writer.println("Hello i Am Host");
				writer.flush();
				String message = reader.nextLine();
				System.out.println(message);
			}

		};

	}

	/**
	 * Joining an already existing host 
	 */
	public void joinGame() {
		new Runnable() {

//			@Override
			public void run() {

				try {
					inputStream = nemesis.getInputStream();
					reader = new Scanner(inputStream);
					putputStream = nemesis.getOutputStream();
					writer = new PrintWriter(putputStream);
				} catch (IOException e) {
					// TODO Something with the IOStreams is wrong, how can that
					// be solved??
					e.printStackTrace();
				}

			}
		};
	}
}
