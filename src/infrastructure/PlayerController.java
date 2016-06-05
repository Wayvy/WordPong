package infrastructure;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class PlayerController extends Thread
{
	private InputStream inputStream;
	private Scanner reader;
	private OutputStream putputStream;
	private PrintWriter writer;
	
	public PlayerController(ServerSocket host, Socket nemesis)
	{
		try {
			inputStream = nemesis.getInputStream();
			reader = new Scanner(inputStream);
			putputStream = nemesis.getOutputStream();
			writer = new PrintWriter(putputStream);
			
		} catch (IOException e) {
			// TODO Maybe the client has to resend the dataStreams
			e.printStackTrace();
		}
		
		
	}
}
