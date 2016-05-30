package gui;

import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

/**
 * A dialog, that lets the user input the IP address and the Port of the Server
 * he wants to connect to
 * 
 * @author Wavy
 * @version 0.5
 */
public class ConnectDialog extends JDialog {
	JTextField IPField = new JTextField(15);
	JTextField portField = new JTextField(15);

	public ConnectDialog(String title) {
		setTitle(title);
		JButton connectBtn = new JButton("Connect");

		IPField.setText("Enter IP");
		portField.setText("Enter Port");

		setLayout(new FlowLayout());

		add(connectBtn, FlowLayout.LEFT);
		add(IPField, FlowLayout.LEFT);
		add(portField, FlowLayout.LEFT);

		connectBtn.addActionListener(connect -> connectToHost());
		IPField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent e) {
				IPField.setText("");
			}
		});

		portField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusGained(FocusEvent e) {
				portField.setText("");
			}
		});

		setSize(200, 120);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void connectToHost() {

	}

	public static void main(String[] args) {
		new ConnectDialog("Connect To Host");
	}
}
