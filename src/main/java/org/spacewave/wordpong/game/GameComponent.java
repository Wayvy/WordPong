package org.spacewave.wordpong.game;

import org.spacewave.wordpong.Countdown;
import org.spacewave.wordpong.GameFrame;
import org.spacewave.wordpong.Player;
import org.spacewave.wordpong.WordPass;
import org.spacewave.wordpong.infrastructure.Connection;
import org.spacewave.wordpong.menu.MenuController;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

@Component
public class GameComponent {

    public void sendPass(WordPass pass, Connection connection) {
        PrintWriter printWriter = new PrintWriter(connection.getPutputStream());
        printWriter.print(pass.getWordToPass() + "\n");
        printWriter.flush();
        System.out.println("Has send " + pass.getWordToPass());
    }

    public WordPass tryCatchingPass(GameFrame swingApp, Connection connection, Countdown countdown) {
        Scanner scanner = new Scanner(connection.getInputStream());
        System.out.println(scanner.hasNext());
        String passToCatch = scanner.nextLine();
        WordPass newPass = new WordPass(passToCatch);
        System.out.println("Has received: " + newPass.getWordToPass());
        countdown.SetGameFrame(swingApp);
        countdown.setCount(10);
        countdown.start();
        return newPass;
    }
    public GameFrame getTurn(GameFrame gameFrame, GameController.TypeOffListener typeOffListener, GameController.SendingPassListener sendingPassListener){
        gameFrame.getBtn().setEnabled(true);
        gameFrame.getBtn().removeActionListener(typeOffListener);
        gameFrame.getBtn().addActionListener(sendingPassListener);
        gameFrame.getPlayType().addActionListener(sendingPassListener);

        // Set Text of the FrameComponents
        gameFrame.getInfoLabel().setText("Player picks InWord");
        gameFrame.getResponseLabel().setText("");

        return gameFrame;
    }

    public GameFrame getFirstTurn(GameFrame gameFrame, MenuController menuController, GameController.SendingPassListener sendingPassListener, Player player){
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

    public JFrame getBlock(JFrame jFrame){
        return jFrame;
    }

    public GameFrame getFirstPassivFrame(GameFrame swingApp, MenuController menuController){
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

    public GameFrame passivFrame(GameFrame swingApp, GameController.SendingPassListener sendingPassListener){

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
}
