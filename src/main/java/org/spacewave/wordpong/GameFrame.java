package org.spacewave.wordpong;

import org.spacewave.wordpong.game.GameComponent;
import org.spacewave.wordpong.game.GameController;
import org.spacewave.wordpong.infrastructure.Connection;
import org.spacewave.wordpong.infrastructure.ConnectionController;
import org.spacewave.wordpong.menu.MenuController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
public class GameFrame extends  JFrame{

    private JPanel backPane = new JPanel();
    private JTextField playType = new JTextField(20);
    private JButton btn = new JButton();
    private JLabel infoLabel = new JLabel();
    private JLabel responseLabel = new JLabel("");

    @Autowired
    private MenuController menuController;


    /**
     * Constructor for the GameFrame. Sets the frame title, creates the layout
     * and an instance of FrameStates whom it calls the initFrame(). Getters and
     * setters for the JButton, the JLabels and the JTextField.
     *
     */
    public void createFrameComponents() {

        backPane.setLayout(new BoxLayout(backPane, BoxLayout.Y_AXIS));
        infoLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        responseLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        btn.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

        backPane.add(Box.createRigidArea(new Dimension(0, 5)));
        backPane.add(infoLabel);
        backPane.add(Box.createRigidArea(new Dimension(0, 5)));
        backPane.add(playType);
        playType.setVisible(false);
        backPane.add(Box.createRigidArea(new Dimension(0, 5)));
        backPane.add(responseLabel);
        backPane.add(Box.createRigidArea(new Dimension(0, 5)));
        backPane.add(btn);

        pack();

        add(backPane);

        // ToDo rules schreiben :)

        // Adding menu for rules.
        JMenuBar menu = new JMenuBar();
        setJMenuBar(menu);

        menu.add(Box.createHorizontalGlue());
        menu.setPreferredSize(new Dimension(80, 20));
        JMenu about = new JMenu("About");

        menu.add(about);
        JMenuItem rules = new JMenuItem("Rules");
        about.add(rules);
    }

    public void createAndShowGUI() {
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setResizable(true);
        this.pack();
        this.setVisible(true);
    }

    public void SetMenuFrame() {
        menuController.getMenuFrame(this);
    }

    // Getter und Setter

    public JTextField getPlayType() {
        return playType;
    }

    public void setPlayType(JTextField playType) {
        this.playType = playType;
    }

    public JButton getBtn() {
        return btn;
    }

    public void setBtn(JButton btn) {
        this.btn = btn;
    }

    public JLabel getInfoLabel() {
        return infoLabel;
    }

    public void setInfoLabel(JLabel infoLabel) {
        this.infoLabel = infoLabel;
    }

    public JLabel getResponseLabel() {
        return responseLabel;
    }

    public void setResponseLabel(JLabel responseLabel) {
        this.responseLabel = responseLabel;
    }

    public GameFrame() {
        super("WordPong");
        createFrameComponents();
        createAndShowGUI();
        setSize(new Dimension(200, 150));
    }

    public JPanel getBackPane() {
        return backPane;
    }

    public void setBackPane(JPanel backPane) {
        this.backPane = backPane;
    }
}
