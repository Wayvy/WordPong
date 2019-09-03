package org.spacewave.wordpong.game;

import org.spacewave.wordpong.GameFrame;
import org.spacewave.wordpong.WordPass;
import org.spacewave.wordpong.infrastructure.Connection;
import org.spacewave.wordpong.menu.MenuController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Controller
public class GameController {

    @Autowired
    public GameComponent gameComponent;
    @Autowired
    private MenuController menuController;
    @Autowired
    private GameFrame gameFrame;

    private SendingPassListener sendingPassListener;
    private TypeOffListener typeOffListener;

    public void StartGame(Connection connection) {
        sendingPassListener = new SendingPassListener(connection);
        gameComponent.getFirstTurn(gameFrame, menuController, sendingPassListener);
    }

    public void passivFrame(Connection connection) {
        sendingPassListener = new SendingPassListener(connection);
        gameComponent.getFirstPassivFrame(gameFrame, menuController);
    }

    public void RunGameLoop(Connection connection) {
        while(true){
            WordPass wordPass = gameComponent.tryCatchingPass(gameFrame, connection);
            typeOffListener = new TypeOffListener(gameFrame.getPlayType(), wordPass, gameFrame.getResponseLabel());
            gameComponent.typeOff(gameFrame, wordPass, typeOffListener);
        }
    }

    class TypeOffListener implements ActionListener {

        private WordPass wordPass;
        private JTextField textField;
        private JLabel infoLabel;

        public TypeOffListener(JTextField textField, WordPass wordPass, JLabel infoLabel) {
            this.textField = textField;
            this.wordPass = wordPass;
            this.infoLabel = infoLabel;
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (textField.getText().equals(wordPass.getWordToPass())) {
                infoLabel.setText(infoLabel.getText() + ": Correct");
                gameComponent.getTurn(gameFrame, typeOffListener, sendingPassListener);
                gameFrame.getPlayType().removeActionListener(typeOffListener);
                gameFrame.getBtn().removeActionListener(typeOffListener);
            } else {
                infoLabel.setText(infoLabel.getText() + ": Incorrect");
            }
        }

    }

    class SendingPassListener implements ActionListener {

        private Connection connection;

        public SendingPassListener(Connection connection) {
            this.connection = connection;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            gameComponent.sendPass(new WordPass(gameFrame.getPlayType().getText()), connection);
            gameComponent.passivFrame(gameFrame, sendingPassListener);
            gameFrame.getPlayType().removeActionListener(sendingPassListener);
            gameFrame.getBtn().removeActionListener(sendingPassListener);
        }
    }
}
