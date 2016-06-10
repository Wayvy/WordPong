package gui;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameFrame extends JFrame {

	// Classmember
	private JTextField playType = new JTextField(20);
	private Dimension mainFrame = new Dimension(200, 500);
	private JButton btn = new JButton("main button");
	private JPanel backPane = new JPanel();
	private JLabel infoLabel = new JLabel("Press Start");
	JLabel responseLabel = new JLabel("");
	private GameFrame gframe;
	private FrameStates states;
	
	
	public GameFrame() {
		super("WordPong");
		gframe = this;
		
		backPane.setLayout(new BoxLayout(backPane, BoxLayout.Y_AXIS));
		infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		responseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		backPane.add(Box.createRigidArea(new Dimension(0,5)));
		backPane.add(infoLabel);
		backPane.add(Box.createRigidArea(new Dimension(0,5)));
		backPane.add(playType);
		backPane.add(Box.createRigidArea(new Dimension(0,5)));
		backPane.add(responseLabel);
		backPane.add(Box.createRigidArea(new Dimension(0,5)));
		backPane.add(btn);
		
		
		
		add(backPane);

		// Adding Menu
		JMenuBar menu = new JMenuBar();
		setJMenuBar(menu);
		
		menu.add(Box.createHorizontalGlue());
		menu.setPreferredSize(new Dimension(80,20));
		JMenu about = new JMenu("About");
		
		menu.add(about);
		JMenuItem rules = new JMenuItem("Rules");
		about.add(rules);

		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new HostDialog(gframe);
				
			}
		});
		
		states = new FrameStates(gframe);
		
		// Make it visible
		setResizable(true);
//		setPreferredSize(mainFrame);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}


	public void setPlayType(String playTxt) {
		this.playType.setText(playTxt);
	}


	public void setBtnTxt(String btnTxt) {
		this.btn.setText(btnTxt);
	}


	public void setInfoTxt(String infoTxt) {
		this.infoLabel.setText(infoTxt);
	}


	public void setResponseTxt(String responseTxt) {
		this.responseLabel.setText(responseTxt);
	}

	
	
}
