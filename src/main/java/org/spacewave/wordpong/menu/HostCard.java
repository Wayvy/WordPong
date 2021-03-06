package org.spacewave.wordpong.menu;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.spacewave.wordpong.GameFrame;
import org.spacewave.wordpong.infrastructure.Connection;
import org.spacewave.wordpong.infrastructure.ConnectionController;
import org.spacewave.wordpong.game.TextFieldListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The dialogcard for hosting a game. Asks for the port the host want to use to
 * establish a connection. When pressing the hostBtn the IP and the port for the
 * ongoing server socket will be shown in hostInfo. Creates a new
 * ConectionController calls the hostGame() method
 * 
 * @author Wavy, Fjiz
 * @version 0.72
 * @see ClientCard
 * @see ConnectionController
 */
@SuppressWarnings("serial")
@Service
public class HostCard extends JPanel {

	private JTextField portField = new JTextField(15);
	private JButton hostBtn = new JButton("Host Server");
	private JTextArea hostInfo;
	private int port;

	@Autowired
	private ConnectDialog parent;

	@Autowired
	private GameFrame gameFrame;

	@Autowired
	private ConnectionController connectionController;

	/**
	 * Creates the layout, adds the JTextField for entering the port number.
	 * When the hostBtn is clicked, checks if the input is a valid port number.
	 * Then calls host()
	 * 
	 * @see HostCard host()
	 */
	public HostCard() {

	}

	public HostCard Init(Connection connection, ConnectDialog parent) {

		// portField.setText("11200");

		// Sets Layout
		this.parent = parent;
		setLayout(new FlowLayout());

		// Adds Components
		add(hostBtn, FlowLayout.LEFT);
		add(portField, FlowLayout.LEFT);

		// Adds Listeners
		portField.addFocusListener(new TextFieldListener("Enter Port", portField));

		hostBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					port = Integer.parseInt(portField.getText());
					if (port < 1025) {
						portField.setText("invalid port");
					} else {
						host(connection);
					}
				} catch (NumberFormatException f) {
					portField.setText("invalid input");
				}

			}
		});
		portField.setText("11200");
		return this;
	}

	/**
	 * Hides the input objects and shows the connection specs. Creates a new
	 * thread ConnectionController and calls the hostGame() method.
	 * 
	 * @see ConnectionController
	 * @see org.spacewave.wordpong.infrastructure.ConnectionController hostGame()
	 */
	private void host(Connection connection) {

		hostBtn.setVisible(false);
		portField.setVisible(false);

		hostInfo = new JTextArea(2, 3);
		hostInfo.setEditable(false);
		add(hostInfo, FlowLayout.LEFT);
		String hostIP;
		try {
			hostIP = InetAddress.getLocalHost().getHostAddress();
			connection.setAddress(hostIP);
			hostInfo.setText("IP: " + hostIP + "\nPort: " + port);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.setPort(port);
		connectionController.hostGame(gameFrame, connection);
		//states.startFrame();
		parent.dispose();

	}

}
