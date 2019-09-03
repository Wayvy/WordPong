package org.spacewave.wordpong.menu;

import org.spacewave.wordpong.GameFrame;
import org.springframework.stereotype.Component;

@Component
public class MenuComponent {

    public GameFrame getMenuFrame(GameFrame gameFrame, MenuController.ConnectionDialogueListener openDialogLst) {
        gameFrame.getBtn().addActionListener(openDialogLst);
        gameFrame.getPlayType().addActionListener(openDialogLst);

        // Set Text of the FrameComponents
        gameFrame.getInfoLabel().setText("Press Start");
        gameFrame.getResponseLabel().setText("");
        gameFrame.getBtn().setText("Start Game");
        gameFrame.getPlayType().setEditable(false);

        return gameFrame;
    }

    public GameFrame waitForJoin(GameFrame gameFrame)
    {
        gameFrame.getBtn().setEnabled(false);
        gameFrame.getBtn().setText("");
        gameFrame.getInfoLabel().setText("Wait for other Player to join");

        return gameFrame;
    }
}
