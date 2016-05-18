package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameFrame extends JFrame {

	// Classmember
	JTextField playType = new JTextField();
	Dimension mainFrame = new Dimension(200, 500);
	JButton btn = new JButton("main button");
	JPanel backPane = new JPanel();

	public GameFrame() {
		super("WordPong");
		
		add(backPane, BorderLayout.CENTER);
		backPane.setLayout(new GridLayout(1,3));
		backPane.add(btn);

		// Adding Menu
		JMenuBar menu = new JMenuBar();
		setJMenuBar(menu);
		JMenu about = new JMenu("About");
		menu.add(about);
		JMenuItem rules = new JMenuItem("Rules");
		about.add(rules);

		// Make it visible
		setResizable(false);
		setSize(mainFrame);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

}
