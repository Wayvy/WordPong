package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import infrastructure.ConnectionController;
import listeners.TextFieldListener;

/**
 * 
 * @author Fjiz
 *
 */
public class ClientCard extends JPanel {

	private JTextField ipField = new JTextField(15);
	private JTextField portField = new JTextField(15);
	private JButton joinBtn = new JButton("Join Server");
	private int port;
	private String ip;
	private JTextArea clientInfo;

	public ClientCard() {

		// Sets Layout
		setLayout(new FlowLayout());

		// Adds Components
		add(joinBtn, FlowLayout.LEFT);
		add(portField, FlowLayout.LEFT);
		add(ipField, FlowLayout.LEFT);
		// Adds Listeners
		portField.addFocusListener(new TextFieldListener("Enter Port", portField));
		ipField.addFocusListener(new TextFieldListener("Enter IP", ipField));

		joinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					port = Integer.parseInt(portField.getText());
					ip = ipField.getText();
						if (port < 1025) {
							portField.setText("invalid port");
						}
						else if (!validate(ip)) {
							ipField.setText("invalid IP");
						} 
					else {
						join();
					}
				} catch (NumberFormatException f) {
					portField.setText("invalid input");
				}

			}
		});
	}

	private void join() {

		joinBtn.setVisible(false);
		portField.setVisible(false);
		ipField.setVisible(false);

		clientInfo = new JTextArea(2, 3);
		clientInfo.setEditable(false);
		add(clientInfo, FlowLayout.LEFT);
		String hostIP = ipField.getText();
		clientInfo.setText("IP: " + hostIP + "\nPort: " + port);

		// Alles folgende findet nun im ConnectionController statt 'Wavy'

		ConnectionController playerController = new ConnectionController(port,hostIP);
		playerController.joinGame();

	}

	private static final Pattern PATTERN = Pattern
			.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
	
/**
 * 
 * @param String ip - The String from the ipTextField
 * @return boolean - true if the String matches the IP-Pattern
 * @author http://stackoverflow.com/questions/5667371/validate-ipv4-address-in-java
 */
	public static boolean validate(final String ip) {
		System.out.println(PATTERN.matcher(ip).matches());
		return PATTERN.matcher(ip).matches();
	}
}
