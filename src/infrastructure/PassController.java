package infrastructure;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import game.WordPass;

/**
 * Handles every the datastreams from client to server
 * 
 * @author wavy
 * @version 1.00
 */
public class PassController {

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
		System.out.println("Has send " + pass.getWordToPass());
	}
	
	public WordPass catchPass()
	{
		String passToCatch = scanner.nextLine();
		WordPass newPass = new WordPass(passToCatch);
		System.out.println("Has received: " + newPass.getWordToPass());
		return newPass;
	}

	/**
	 * Right now the Server takes the data and puts it out on its console
	 */

}
