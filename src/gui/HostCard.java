package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import infrastructure.ConnectionController;
import listeners.TextFieldListener;

/**
 * The dialogcard, that asks for the Port the Host want to use to get a
 * connection. When pressing the hostBtn, a Thread will be created and lets some
 * other player connect to the host. While waiting the Information to connect
 * will be output at the HostGUI
 * 
 * @author Wavy, Fjiz
 * @version 0.71
 */
public class HostCard extends JPanel {

	private JTextField portField = new JTextField(15);
	private JButton hostBtn = new JButton("Host Server");
	private JTextArea hostInfo;
	private int port;
	private ConnectDialog parent;

	/**
	 * Creates the Hostcard, that asks for a Port number and checks if the input
	 * is a valid port number. Afterwards a new ServerSocket is created.
	 * @param states 
	 * 
	 * @param the
	 *            parent frame
	 */
	public HostCard(ConnectionController hostController, FrameStates states, ConnectDialog parent) {

//		portField.setText("11200");
		

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
						host(hostController, states);
					}
				} catch (NumberFormatException f) {
					portField.setText("invalid input");
				}

			}
		});
		portField.setText("11200");
	}

	/**
	 * Changes the state of the Dialog to output the Information to connect to
	 * the server
	 */
	private void host(ConnectionController hostController, FrameStates states) {

		hostBtn.setVisible(false);
		portField.setVisible(false);

		hostInfo = new JTextArea(2, 3);
		hostInfo.setEditable(false);
		add(hostInfo, FlowLayout.LEFT);
		String hostIP;
		try {
			hostIP = InetAddress.getLocalHost().getHostAddress();
			hostInfo.setText("IP: " + hostIP + "\nPort: " + port);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hostController.setPort(port);
		hostController.hostGame();
		states.startFrame();
		parent.dispose();


	}

}
