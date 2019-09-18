package org.spacewave.wordpong.game;

import javafx.animation.KeyValue;
import org.spacewave.wordpong.Countdown;
import org.spacewave.wordpong.GameFrame;
import org.spacewave.wordpong.Player;
import org.spacewave.wordpong.WordPass;
import org.spacewave.wordpong.infrastructure.Connection;
import org.spacewave.wordpong.infrastructure.ConnectionController;
import org.spacewave.wordpong.menu.MenuController;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.security.KeyPair;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class GameComponent {

    public static final String RECEIVE_CODE_WORD = "word";
    public static final String RECEIVE_CODE_STATUS = "status";

    public void sendPass(WordPass pass, Connection connection) {
        PrintWriter printWriter = new PrintWriter(connection.getPutputStream());
        printWriter.print(RECEIVE_CODE_WORD + "|" + pass.getWordToPass() + "\n");
        printWriter.flush();
    }

    public Map.Entry<String, String> tryCatchingPass(GameFrame swingApp, Connection connection, Countdown countdown, ConnectionController connectionController) {
        Scanner scanner = new Scanner(connection.getInputStream());
        String passToCatch = scanner.nextLine();
        if (passToCatch.contains(RECEIVE_CODE_STATUS + "|end")) {
            return Map.entry(RECEIVE_CODE_STATUS, "");
        } else {
            return Map.entry(RECEIVE_CODE_WORD, catchPass(swingApp, connection, countdown, passToCatch.split("\\|")[1], connectionController));
        }
    }

    private String catchPass(GameFrame swingApp, Connection connection, Countdown countdown, String passToCatch, ConnectionController connectionController) {
        swingApp.getBtn().setEnabled(true);
        swingApp.getPlayType().setEnabled(true);
        countdown.SetGameFrame(swingApp);
        countdown.SetConnection(connection);
        countdown.SetConnectionController(connectionController);
        countdown.setYourTurn(true);
        countdown.setCount(10);
        countdown.start();
        return passToCatch;
    }

    public GameFrame getTurn(GameFrame gameFrame, GameController.TypeOffListener typeOffListener, GameController.SendingPassListener sendingPassListener) {
        gameFrame.getBtn().setEnabled(true);
        gameFrame.getBtn().removeActionListener(typeOffListener);
        gameFrame.getBtn().addActionListener(sendingPassListener);
        gameFrame.getPlayType().addActionListener(sendingPassListener);

        // Set Text of the FrameComponents
        gameFrame.getInfoLabel().setText("Player picks InWord");
        gameFrame.getResponseLabel().setText("");

        return gameFrame;
    }

    public GameFrame getFirstTurn(GameFrame gameFrame, MenuController menuController, GameController.SendingPassListener sendingPassListener, Player player) {
        //Setup Listeners
        gameFrame.getBtn().setEnabled(true);
        gameFrame.getBtn().removeActionListener(menuController.getConnectionDialogueListener());
        gameFrame.getPlayType().setVisible(true);
        gameFrame.getPlayType().removeActionListener(menuController.getConnectionDialogueListener());
        gameFrame.getBtn().addActionListener(sendingPassListener);
        gameFrame.getPlayType().addActionListener(sendingPassListener);

        // Set Text of the FrameComponents
        gameFrame.getInfoLabel().setText(player.getName() + " picks Word");
        gameFrame.getResponseLabel().setText("");
        gameFrame.getBtn().setText("Begin");

        gameFrame.getPlayType().setEditable(true);
        return gameFrame;
    }

    public GameFrame getFirstPassivFrame(GameFrame swingApp, MenuController menuController) {
        swingApp.getBtn().removeActionListener(menuController.getConnectionDialogueListener());
        swingApp.getPlayType().removeActionListener(menuController.getConnectionDialogueListener());

        // Set Text of the FrameComponents
        swingApp.getInfoLabel().setText("Wait for Other Player to make Turn");
        swingApp.getResponseLabel().setText("");
        swingApp.getBtn().setText("");
        swingApp.getPlayType().setEditable(false);
        swingApp.getPlayType().setText("");

        return swingApp;
    }

    public GameFrame passivFrame(GameFrame swingApp, GameController.SendingPassListener sendingPassListener) {

        swingApp.getPlayType().removeActionListener(sendingPassListener);
        swingApp.getBtn().removeActionListener(sendingPassListener);

        // Set Text of the FrameComponents
        swingApp.getInfoLabel().setText("Wait for Other Player to make Turn");
        swingApp.getResponseLabel().setText("");
        swingApp.getBtn().setText("");
        swingApp.getPlayType().setEditable(false);
        swingApp.getPlayType().setText("");

        return swingApp;
    }

    public GameFrame typeOff(GameFrame gameFrame, WordPass wordPass, GameController.TypeOffListener typeOffListener) {

        // Set Text of the FrameComponents
        gameFrame.getInfoLabel().setText("Type off the Word:");
        gameFrame.getResponseLabel().setText(wordPass.getWordToPass());
        gameFrame.getBtn().setText("Type Off");
        gameFrame.getPlayType().setVisible(true);
        gameFrame.getPlayType().setEditable(true);
        gameFrame.getPlayType().setText("");

        gameFrame.getBtn().addActionListener(typeOffListener);
        gameFrame.getPlayType().addActionListener(typeOffListener);

        return gameFrame;
    }

    public void sendGameOver(Connection connection) {
        PrintWriter printWriter = new PrintWriter(connection.getPutputStream());
        printWriter.print(RECEIVE_CODE_STATUS + "|end" + "\n");
        printWriter.flush();
    }
}
