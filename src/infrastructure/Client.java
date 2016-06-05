package infrastructure;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread
{
	private InputStream inputStream;
	private Scanner reader;
	private OutputStream outputStream;
	private PrintWriter printer;
	
	public Client(Socket hero)
	{
		try {
			inputStream = hero.getInputStream();
			reader = new Scanner(inputStream);
			outputStream = hero.getOutputStream();
			printer = new PrintWriter(outputStream);
		} catch (IOException e) {
			// TODO Something with the IOStreams is wrong, how can that be solved??
			e.printStackTrace();
		}
		
	}
}
