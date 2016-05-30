package gui;

import java.awt.FlowLayout;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A dialog, that asks for the Port the Host want to use to get a connection.
 * When pressing the hostBtn, a Thread will be created and lets some other
 * player connect to the host. While waiting the Information to connect will be
 * output at the HostGUI
 * 
 * @author Wavy
 * @version 0.5
 */
public class HostDialog extends JDialog {

	JTextField addressField = new JTextField(15);
	JButton hostBtn = new JButton("Host Server");

	/**
	 * Opens a Dialog, that asks for a Port number
	 * 
	 * @param title
	 *            - The Title of the Dialog
	 */
	public HostDialog(String title) {
		setTitle(title);

		setLayout(new FlowLayout());

		add(hostBtn, FlowLayout.LEFT);
		add(addressField, FlowLayout.LEFT);

		hostBtn.addActionListener(HostServer -> host());

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
			addressField.setVisible(false);
			hostBtn.setVisible(false);
			JTextArea hostInfo = new JTextArea(2, 3);
			hostInfo.setText("IP: " + hostIP + "\nPort: " + port);
			hostInfo.setEditable(false);
			add(hostInfo);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new HostDialog("Host Server");

	}
}
