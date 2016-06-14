package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.ConnectDialog;
import gui.FrameStates;
import gui.GameFrame;
import infrastructure.ConnectionController;

public class ConnectioDialogioListener implements ActionListener {
	
	private GameFrame gFrame;
	private FrameStates states;
	private ConnectionController connectionController;
	
	public ConnectioDialogioListener(GameFrame gFrame, FrameStates states,ConnectionController connectionController ) {
		this.gFrame = gFrame;
		this.connectionController = connectionController;
		this.states = states;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new ConnectDialog(gFrame, states, connectionController);

	}

}
