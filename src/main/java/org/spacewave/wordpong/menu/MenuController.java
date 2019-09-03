package org.spacewave.wordpong.menu;

import org.spacewave.wordpong.GameFrame;
import org.spacewave.wordpong.infrastructure.Connection;
import org.spacewave.wordpong.infrastructure.ConnectionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Controller
public class MenuController {

    private ConnectionDialogueListener connectionDialogueListener;

    @Autowired
    private MenuComponent menuComponent;

    @Autowired
    private ConnectionController connectionController;

    @Autowired
    private ConnectDialog connectDialog;

    public MenuController(){
        connectionDialogueListener = new ConnectionDialogueListener(new Connection());
    }

    public GameFrame getMenuFrame(GameFrame gameFrame) {
        menuComponent.getMenuFrame(gameFrame, connectionDialogueListener);
        return gameFrame;
    }

    public GameFrame waitForJoin(GameFrame gameFrame) {
        menuComponent.getMenuFrame(gameFrame, connectionDialogueListener);
        return gameFrame;
    }

    public ConnectionDialogueListener getConnectionDialogueListener() {
        return connectionDialogueListener;
    }

    class ConnectionDialogueListener implements ActionListener {
        private Connection connection;

        public ConnectionDialogueListener(Connection connection) {
            this.connection = connection;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            connectDialog.Init();
        }
    }
}
