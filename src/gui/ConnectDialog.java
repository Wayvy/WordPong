package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import infrastructure.ConnectionController;
/**
 * This dialog lets you choose between hosting or joining a game
 * through two RadioButtons which call for their card.
 * 
 * @author Fjiz
 * @version 0.1
 */
public class ConnectDialog extends JDialog implements ActionListener {
	
//	private GameFrame gframe;
	private JPanel cards;
	private ConnectionController connectionController; 
	JRadioButton hostBtn = new JRadioButton("Host Game", true);
	JRadioButton joinBtn = new JRadioButton("Join Game", false);
	
	/**creates the dialog. The host card is the choosen 
	 * by default.
	 * 
	 * @param gframe - the parent which invokes the dialog
	 * @param states 
	 */
	public ConnectDialog(GameFrame gframe, FrameStates states, ConnectionController connectionController) {
		super(gframe);
//		this.gframe = gframe;
		this.connectionController = connectionController;

		addComponentToPane(getContentPane(), states);
		setModal(true);
		setLocationRelativeTo(gframe);
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public void addComponentToPane(Container pane, FrameStates states) {
		JPanel radioButtonPane = new JPanel();

		hostBtn.addActionListener(this);
		joinBtn.addActionListener(this);
		// make a ButtonGroup
		ButtonGroup group = new ButtonGroup();
		group.add(hostBtn);
		group.add(joinBtn);

		radioButtonPane.add(hostBtn);
		radioButtonPane.add(joinBtn);

		// the host card
		HostCard hostDialog = new HostCard(connectionController,states,  this);
		ClientCard clientCard = new ClientCard(connectionController,states, this);

		
		// creating the CardLayout
		cards = new JPanel(new CardLayout());
		cards.add(hostDialog, "card1");
		cards.add(clientCard, "card2");

		pane.add(radioButtonPane, BorderLayout.PAGE_START);
		pane.add(cards, BorderLayout.CENTER);

	}

//	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout cl = (CardLayout) (cards.getLayout());
		if (e.getSource() == hostBtn) {
			cl.show(cards, "card1");
		} else if (e.getSource() == joinBtn) {
			cl.show(cards, "card2");
		}
	}
	
}
