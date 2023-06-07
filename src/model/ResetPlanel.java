package model;

import service.GameService;

import javax.swing.*;
import java.awt.*;

public class ResetPlanel {

    private GameService gameService;
    private JButton resetButton;
    public ResetPlanel(GameService gameService) {
        resetButton = new JButton();
        this.gameService = gameService;

        resetButton.addActionListener(this.gameService);
        resetButton.setText("reset");
        resetButton.setFont(new Font("Ink Free", Font.BOLD, 50));
    }

    public JButton getResetButton() {
        return resetButton;
    }
}
