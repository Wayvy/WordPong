package infrastructure;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import game.WordPass;

/**
 * Handles every Request from a Client to send a Message to the Chat
 * 
 * @author wavy
 * @version 1.00
 */
public class PassController extends Thread {

	private PrintWriter output;
	private Scanner scanner;
	private String name;
	private Socket nemesis;
	int index = 0;

	/**
	 * 
	 * @param nemesis
	 * @param name
	 * @param output
	 * @param scanner
	 * @throws IOException
	 */
	public PassController(Socket nemesis, String name, PrintWriter output, Scanner scanner)
			throws IOException {
		this.nemesis = nemesis;
		this.output = output;
		this.scanner = scanner;
		this.name = name;
		System.out.print("Gui Controller Created");
		

		// Fill the receivers Array with all the clients except source of the
		// message

	}

	public void sendPass(WordPass pass) {
		output.write(pass.getWordToPass());
		output.flush();
	}
	
	private void catchPass(String wordToPass)
	{
		WordPass newPass = new WordPass(scanner.nextLine());
	}

	/**
	 * Right now the Server takes the data and puts it out on its console
	 */
	@Override
	public void run() {
		
			while(true)
			{
				if(scanner.hasNext())
				{
					catchPass(scanner.nextLine());
					break;
				}
			}


	}
}
