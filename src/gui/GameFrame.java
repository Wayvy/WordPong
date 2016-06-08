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
	JTextField playType = new JTextField(20);
	Dimension mainFrame = new Dimension(200, 500);
	JButton btn = new JButton("main button");
	JPanel backPane = new JPanel();
	JLabel infoLabel = new JLabel("Press Start");
//	JLabel responseLabel = new JLabel("");
	
	
	
	public GameFrame() {
		super("WordPong");
		
		
		backPane.setLayout(new BoxLayout(backPane, BoxLayout.Y_AXIS));
		infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//		responseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn.setAlignmentX(Component.CENTER_ALIGNMENT);
		infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		backPane.add(Box.createRigidArea(new Dimension(0,5)));
		backPane.add(infoLabel);
//		backPane.add(Box.createRigidArea(new Dimension(0,5)));
//		backPane.add(responseLabel);
		backPane.add(Box.createRigidArea(new Dimension(0,5)));
		backPane.add(playType);
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
				new HostDialog();
				
			}
		});
		
		// Make it visible
		setResizable(true);
//		setPreferredSize(mainFrame);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

}
