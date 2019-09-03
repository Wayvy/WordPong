package org.spacewave.wordpong.menu;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.spacewave.wordpong.ApplicationContextProvider;
import org.spacewave.wordpong.GameFrame;
import org.spacewave.wordpong.SwingApp;
import org.spacewave.wordpong.game.GameComponent;
import org.spacewave.wordpong.game.GameController;
import org.spacewave.wordpong.infrastructure.Connection;
import org.spacewave.wordpong.infrastructure.ConnectionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * This dialog lets you choose between hosting or joining a game through two
 * RadioButtons which call for their card.
 * 
 * @author Fjiz
 * @version 0.3
 * @see HostCard HostCard()
 * @see ClientCard ClientCard()
 */

@Service
public class ConnectDialog extends JDialog implements ActionListener {

	private JPanel cards;
	private JRadioButton hostBtn = new JRadioButton("Host Game", true);
	private JRadioButton joinBtn = new JRadioButton("Join Game", false);

	@Autowired
	private ApplicationContextProvider applicationContextProvider;

	@Autowired
	private HostCard hostCard;

	@Autowired
	private ClientCard clientCard;


	/**
	 * creates the dialog. The host card is the choosen by default. The Dialog
	 * is modal and shows on top of the invoking parent
	 * 
	 *
	 *            - the parent which invokes the dialog
	 * @see SwingApp GameFrame()
	 */
	public ConnectDialog() {

	}

	public void Init() {
		GameFrame gameFrame = applicationContextProvider.getApplicationContext().getBean(GameFrame.class);
		new JDialog(gameFrame);
		addComponentToPane(getContentPane(), gameFrame);
		setModal(true);
		setLocationRelativeTo(gameFrame);
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

    /**
	 * Fills the dialog with its content
	 * 
	 * @param pane
	 *            - the container to add the content
	 */
	public void addComponentToPane(Container pane, GameFrame gameFrame) {

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
		HostCard hostDialog = hostCard.Init(new Connection(), this);
		ClientCard clientDialog = clientCard.Init(new Connection(),  this);

		// creating the CardLayout
		cards = new JPanel(new CardLayout());
		cards.add(hostDialog, "card1");
		cards.add(clientCard, "card2");

		pane.add(radioButtonPane, BorderLayout.PAGE_START);
		pane.add(cards, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout cl = (CardLayout) (cards.getLayout());
		if (e.getSource() == hostBtn) {
			cl.show(cards, "card1");
		} else if (e.getSource() == joinBtn) {
			cl.show(cards, "card2");
		}
	}
}
