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
	private JTextArea hostInfo;
	private int port;
	/**
	 * Opens a Dialog, that asks for a Port number
	 * fix dialog name "Host Server"
	 * checks if input is a valid port number
	 * @param the parent frame
	 */
	public HostDialog(GameFrame gframe) {
		// Sets Layout
		super(gframe);
		setTitle("Host Server");
		setModal(true);
		setLocationRelativeTo(gframe);
		setLayout(new FlowLayout());

		// Adds Components
		add(hostBtn, FlowLayout.LEFT);
		add(addressField, FlowLayout.LEFT);
		// Adds Listeners
		addressField.addFocusListener(new TextFieldListener("Enter Port", addressField));
		hostBtn.addActionListener(new ActionListener() {
		// Bin zu bloed den Defaultfocus auf den Button zu legen
			public void actionPerformed(ActionEvent arg0) {
				try{
					port = Integer.parseInt(addressField.getText());
					if(port < 1025){
						addressField.setText("invalid port");
					}
					else{
						host();
					}
				} catch(NumberFormatException f){
					addressField.setText("invalid input");
					}
					
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
	
		
		try {

			// Adjust Dialog < funktioniert nicht 'fjiz'
			hostBtn.setVisible(false);
			addressField.setVisible(false);
			
			// Create HostInfo < funktioniert nicht 'fjiz'
			hostInfo = new JTextArea(2, 3);
			hostInfo.setEditable(false);
			add(hostInfo, FlowLayout.LEFT);
			String hostIP = InetAddress.getLocalHost().getHostAddress();
			hostInfo.setText("IP: " + hostIP + "\nPort: " + port);
			// 
			ServerSocket host = new ServerSocket(port,1);
			System.out.println("test");
			Socket nemesis = host.accept();
			
			// Alles folgende findet erst mit Client statt 'fjiz'
			
			PlayerController playerController= new PlayerController(host, nemesis);
			playerController.start();
			// Threads!!

		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.out.println("Unknown Host Socket");
		} catch (IOException e) {
			System.out.println("IO Exception Socket");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
//		new HostDialog();

	}
}
