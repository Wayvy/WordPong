package org.spacewave.wordpong.game;

import org.spacewave.wordpong.Countdown;
import org.spacewave.wordpong.GameFrame;
import org.spacewave.wordpong.Player;
import org.spacewave.wordpong.WordPass;
import org.spacewave.wordpong.infrastructure.Connection;
import org.spacewave.wordpong.menu.MenuController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import static org.spacewave.wordpong.game.GameComponent.RECEIVE_CODE_WORD;

@Controller
public class GameController {

    @Autowired
    public GameComponent gameComponent;
    @Autowired
    private MenuController menuController;
    @Autowired
    private GameFrame gameFrame;
    @Autowired
    private Countdown countdown;

    private SendingPassListener sendingPassListener;
    private TypeOffListener typeOffListener;

    public void StartGame(Connection connection, Player player) {
        sendingPassListener = new SendingPassListener(connection, countdown);
        gameComponent.getFirstTurn(gameFrame, menuController, sendingPassListener, player);
    }

    public void passivFrame(Connection connection) {
        sendingPassListener = new SendingPassListener(connection, countdown);
        gameComponent.getFirstPassivFrame(gameFrame, menuController);
    }

    public void RunGameLoop(Connection connection, Player player) {
        while(true){
            Map.Entry<String, String> received = gameComponent.tryCatchingPass(gameFrame, connection, countdown);
            if(received.getKey().equals(RECEIVE_CODE_WORD)){
                WordPass wordPass = new WordPass(received.getValue());
                typeOffListener = new TypeOffListener(gameFrame.getPlayType(), wordPass, gameFrame.getResponseLabel(), player);
                gameComponent.typeOff(gameFrame, wordPass, typeOffListener);
            } else {
                GetWinningScreen();
            }
        }
    }

    public void GetGameOver(Connection connection){
        System.out.println("You lost");
        gameFrame.getBtn().setEnabled(false);
        gameFrame.getPlayType().setEnabled(false);
        gameComponent.sendGameOver(connection);
    }

    public void GetWinningScreen(){
        System.out.println("You win");
    }

    class TypeOffListener implements ActionListener {

        private WordPass wordPass;
        private JTextField textField;
        private JLabel infoLabel;
        private Player player;

        public TypeOffListener(JTextField textField, WordPass wordPass, JLabel infoLabel, Player player) {
            this.textField = textField;
            this.wordPass = wordPass;
            this.infoLabel = infoLabel;
            this.player = player;
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (textField.getText().equals(wordPass.getWordToPass())) {
                infoLabel.setText(infoLabel.getText() + ": Correct");
                textField.setText("");
                player.increaseScore(100 * wordPass.getMaxPoints());
                gameFrame.setPointsLabel(player.getScore());
                gameComponent.getTurn(gameFrame, typeOffListener, sendingPassListener);
                gameFrame.getPlayType().removeActionListener(typeOffListener);
                gameFrame.getBtn().removeActionListener(typeOffListener);
            } else {
                infoLabel.setText(infoLabel.getText() + ": Incorrect");
                player.increaseScore(- 100 / wordPass.getMaxPoints());
                gameFrame.setPointsLabel(player.getScore());
                textField.setText("");
            }
        }

    }

    class SendingPassListener implements ActionListener {

        private Connection connection;
        private Countdown countdown;

        public SendingPassListener(Connection connection, Countdown countdown) {
            this.countdown = countdown;
            this.connection = connection;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            gameComponent.sendPass(new WordPass(gameFrame.getPlayType().getText()), connection);
            if(countdown.isRunning()){
                countdown.interrupt();
            }
            gameComponent.passivFrame(gameFrame, sendingPassListener);
        }
    }
}
