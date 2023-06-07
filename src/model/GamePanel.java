package model;

import service.GameService;

import javax.swing.*;
import java.awt.*;

public class GamePanel {
    private JPanel game;
    private JPanel headers;
    private JLabel label;
    private JButton[] buttons;

    public GamePanel(GameService gameService) {
        game = new JPanel();
        headers = new JPanel();
        label = new JLabel();
        buttons = new JButton[9];

        label.setBackground(new Color(0, 0, 0));
        label.setForeground(new Color(225, 1, 100));
        label.setFont(new Font("Ink Free", Font.BOLD, 75));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setOpaque(true);

        headers.setLayout(new BorderLayout());
        headers.setBounds(0, 0, 800, 100);
        headers.add(label);

        game.setLayout(new GridLayout(3, 3));
        initializeButtons(gameService);
    }

    private void initializeButtons(GameService gameService) {
        game.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            game.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(gameService);
        }
    }

    public JLabel getLabel(){
        return label;
    }

    public JPanel getPanel(){
        return game;
    }

    public JPanel getHeaders(){
        return headers;
    }

    public JButton getJbutton(int i){
        return buttons[i];
    }
}
