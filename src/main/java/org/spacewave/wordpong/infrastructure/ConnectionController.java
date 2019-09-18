package org.spacewave.wordpong.infrastructure;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.spacewave.wordpong.GameFrame;
import org.spacewave.wordpong.Player;
import org.spacewave.wordpong.WordPass;
import org.spacewave.wordpong.game.GameController;
import org.spacewave.wordpong.menu.MenuComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * Handles connecting every request to join a server or host one.
 * 
 * @author Wavy
 * @version 0.7
 */

@Controller
public class ConnectionController extends Thread {

	@Autowired
	private MenuComponent menuComponent;

	@Autowired
	private GameController gameController;

	private Thread worker;

	/**
	 * Host the game and lets other people connect to this Socket. Runs in the
	 * background, and checks the connection and creates the GameThread, once
	 * the connection to the other player has been accepted.
	 */
	public void hostGame(GameFrame gameFrame, Connection connection) {
		worker = new Thread(() -> {
			try {
				menuComponent.waitForJoin(gameFrame);
				ServerSocket host = new ServerSocket(connection.getPort(), 100);
				connection.setNameHost(JOptionPane.showInputDialog("Please enter your name"));
				connection.setNemesis(host.accept());

				setupFileIO(connection);

				connection.getWriter().println("Hello, here is " + connection.getNameHost() + " !");
				connection.getWriter().flush();

				String message = connection.getReader().nextLine();
				System.out.println(message);

				Player player = new Player(connection.getNameHost());
				gameController.StartGame(connection, player);
				gameController.RunGameLoop(connection, player, this);

				while (true) {
					if (interrupted())
						break;
				}
				host.close();
			} catch (IOException e) {
				// TODO Maybe the client has to resends the dataStreams
				e.printStackTrace();
			} finally {
			}
		});
		worker.start();
	};

	/**
	 * Joining an already existing host
	 */
	public void joinGame(Connection connection) {
		// @Override
		Thread joinThread = new Thread(() -> {

			try {
				connection.setNameClient(JOptionPane.showInputDialog("Please enter your name"));

				// Setup Socket
				connection.setNemesis(new Socket(InetAddress.getByName(connection.getAddress()), connection.getPort()));

				setupFileIO(connection);

				// Write to Server
				connection.getWriter().println("Hello, here is " + connection.getNameClient() + " !");
				connection.getWriter().flush();

				// Read from server
				String message = connection.getReader().nextLine();
				System.out.println(message);

				Player player = new Player(connection.getNameClient());

				gameController.passivFrame(connection);
				gameController.RunGameLoop(connection, player, this);

				while(true)
				{
					if(interrupted());
					break;
				}

			} catch (IOException e) {
				// TODO Something with the IOStreams is wrong, how can that
				// be solved??
				e.printStackTrace();
			}

		});

		joinThread.start();
	}

	private void setupFileIO(Connection connection) {
		try {
			// Setup receiving Messages
			connection.setInputStream(connection.getNemesis().getInputStream());
			connection.setReader(new Scanner(connection.getInputStream()));

			// Setup sending Messages
			connection.setPutputStream(connection.getNemesis().getOutputStream());
			connection.setWriter(new PrintWriter(connection.getPutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ConnectionController(MenuComponent menuComponent, GameController gameController){
		this.menuComponent = menuComponent;
		this.gameController = gameController;
	}
}
