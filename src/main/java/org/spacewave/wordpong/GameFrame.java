package org.spacewave.wordpong;

import org.spacewave.wordpong.game.GameComponent;
import org.spacewave.wordpong.game.GameController;
import org.spacewave.wordpong.infrastructure.Connection;
import org.spacewave.wordpong.infrastructure.ConnectionController;
import org.spacewave.wordpong.menu.MenuController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class GameFrame extends JFrame {

    private JPanel backPane = new JPanel();
    private JTextField playType = new JTextField(20);
    private JPanel graphicsPanel = new JPanel();
    private JButton btn = new JButton();
    private JLabel infoLabel = new JLabel();
    private JLabel countDownLabel = new JLabel();
    private JLabel responseLabel = new JLabel("");
    private JLabel pointsLabel = new JLabel("");
    private JPanel graphic;

    @Autowired
    private MenuController menuController;


    /**
     * Constructor for the GameFrame. Sets the frame title, creates the layout
     * and an instance of FrameStates whom it calls the initFrame(). Getters and
     * setters for the JButton, the JLabels and the JTextField.
     */
    public void createFrameComponents() {

        this.backPane = new JPanel();
        this.backPane.setLayout(new GridBagLayout());
        this.btn = new JButton();
        this.btn.setText("Button");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0D;
        gbc.weighty = 1.0D;
        gbc.fill = 2;
        this.backPane.add(this.btn, gbc);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = 1;
        this.backPane.add(panel2, gbc);
        this.pointsLabel = new JLabel();
        this.pointsLabel.setText("Score: 0");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0D;
        gbc.weighty = 1.0D;
        gbc.anchor = 17;
        panel2.add(this.pointsLabel, gbc);
        this.countDownLabel = new JLabel();
        this.countDownLabel.setText("Countdown: 0");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 1.0D;
        gbc.weighty = 1.0D;
        gbc.anchor = 13;
        panel2.add(this.countDownLabel, gbc);
        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weighty = 1.0D;
        gbc.fill = 1;
        panel2.add(panel3, gbc);
        this.responseLabel = new JLabel();
        this.responseLabel.setText("Word to Type");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        panel2.add(this.responseLabel, gbc);
        this.playType = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = 17;
        gbc.fill = 2;
        this.backPane.add(this.playType, gbc);
        JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(1, 5, 5));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = 1;
        this.backPane.add(panel4, gbc);
        this.infoLabel = new JLabel();
        this.infoLabel.setText("Response Label");
        panel4.add(this.infoLabel);

        add(backPane);

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

    public void setPointsLabel(int points) {
        pointsLabel.setText("Score: " + points);
    }

    public GameFrame() {
        super("WordPong");
        createFrameComponents();
        createAndShowGUI();
        setSize(new Dimension(200, 150));
    }

    public JLabel getCountDownLabel() {
        return countDownLabel;
    }

    public void setCountDownLabel(JLabel countDownLabel) {
        this.countDownLabel = countDownLabel;
    }

    public JPanel getBackPane() {
        return backPane;
    }

    public void setBackPane(JPanel backPane) {
        this.backPane = backPane;
    }
}
