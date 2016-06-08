package gui;


import infrastructure.PlayerController;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import listeners.TextFieldListener;

/**
 * A dialog, that asks for the Port the Host want to use to get a connection.
 * When pressing the hostBtn, a Thread will be created and lets some other
 * player connect to the host. While waiting the Information to connect will be
 * output at the HostGUI
 * 
 * @author Wavy, Fjiz
 * @version 0.6
 */
public class HostDialog extends JDialog {

	private JTextField addressField = new JTextField(15);
	private JButton hostBtn = new JButton("Host Server");

	/**
	 * Opens a Dialog, that asks for a Port number
	 * fix dialog name "Host Server"
	 */
	public HostDialog() {
		// Sets Layout
		setTitle("Host Server");
		setModal(true);
		setLayout(new FlowLayout());

		// Adds Components
		add(hostBtn, FlowLayout.LEFT);
		add(addressField, FlowLayout.LEFT);

		// Adds Listeners
		addressField.addFocusListener(new TextFieldListener("Enter Port", addressField));
		hostBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				host();
			}
		});

		// Sets Configurations
		setSize(200, 100);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	/**
	 * Changes the state of the Dialog to output the Information to connect to
	 * the server
	 */
	private void host() {
		int port = Integer.parseInt(addressField.getText());
		try {
			String hostIP = InetAddress.getLocalHost().getHostAddress();
			// Adjust Dialog
			addressField.setVisible(false);
			hostBtn.setVisible(false);

			// Create HostInfo
			JTextArea hostInfo = new JTextArea(2, 3);
			hostInfo.setText("IP: " + hostIP + "\nPort: " + port);
			hostInfo.setEditable(false);
			add(hostInfo);
			ServerSocket host = new ServerSocket(11200,100);
			Socket nemesis = host.accept();
			PlayerController playerController= new PlayerController(host, nemesis);
			// Threads!!

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Something with the Socket");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new HostDialog();

	}
}
