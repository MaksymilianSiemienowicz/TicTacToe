package service;

import model.GamePanel;
import model.ResetPlanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameService implements ActionListener {
   private JFrame frame = new JFrame();
    private Random randomNumber = new Random();

    private JFrame resetFrame = new JFrame();


    private boolean xTurn;
    private boolean vsComputer;

    GamePanel gamePanel;
    ResetPlanel resetPlanel = new ResetPlanel(this);


    public GameService() {
        gamePanel = new GamePanel(this);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(50, 25, 50));
        resetFrame.setSize(200, 200);
        resetFrame.dispose();

        resetFrame.add(resetPlanel.getResetButton());
        frame.add(gamePanel.getPanel());
        frame.add(gamePanel.getHeaders(), BorderLayout.NORTH);

        chooseMode();
    }

    public void setxTurn(boolean xTurn) {
        this.xTurn = xTurn;
    }

    public boolean isxTurn() {
        return xTurn;
    }

    public boolean isVsComputer() {
        return vsComputer;
    }


    private void chooseMode() {
        Object[] options = {"Player vs Player", "Player vs Computer"};
        int choice = JOptionPane.showOptionDialog(null, "Select game mode", "Game Mode", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            vsComputer = false;
            frame.setVisible(true);
            xTurn = (randomNumber.nextInt(2) == 0);
            if (xTurn) {
                gamePanel.getLabel().setText("X turn (X)");
            } else {
                gamePanel.getLabel().setText("O turn (O)");
            }
        } else {
            vsComputer = true;
            frame.setVisible(true);
            xTurn = (randomNumber.nextInt(2) == 0);
            if (xTurn) {
                gamePanel.getLabel().setText("Your turn (X)");
            } else {
                gamePanel.getLabel().setText("Computer's turn (O)");
                computerMove();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (vsComputer) {
            // Player vs Computer mode

            // Player's move
            for (int i = 0; i < 9; i++) {
                if (e.getSource() == gamePanel.getJbutton(i)) {
                    if (gamePanel.getJbutton(i).getText().isEmpty()) {
                        gamePanel.getJbutton(i).setForeground(new Color(225, 0, 0));
                        gamePanel.getJbutton(i).setText("X");
                        xTurn = false; // Toggle the turn
                        gamePanel.getLabel().setText("Computer's turn (O)");
                        checkWin();

                        // Computer's move
                        computerMove();
                    }
                }
            }
        }  else {
            // Player vs Player mode
            for (int i = 0; i < 9; i++) {
                if (e.getSource() == gamePanel.getJbutton(i)) {
                    handlePlayerMove(i);
                    break;
                }
            }
        }


        if(e.getSource() == resetPlanel.getResetButton()){
            frame.dispose();
            new GameService();
            resetFrame.dispose();
        }

    }

    private void handlePlayerMove(int buttonIndex) {
        if (gamePanel.getJbutton(buttonIndex).getText().isEmpty()) {
            if (xTurn) {
                gamePanel.getJbutton(buttonIndex).setForeground(new Color(225, 0, 0));
                gamePanel.getJbutton(buttonIndex).setText("X");
                gamePanel.getLabel().setText("O's turn");
            } else {
                gamePanel.getJbutton(buttonIndex).setForeground(new Color(50, 50, 70));
                gamePanel.getJbutton(buttonIndex).setText("O");
                gamePanel.getLabel().setText("X's turn");
            }
            xTurn = !xTurn;
            checkWin();
        }
    }

    public void computerMove() {
        if (isBoardFull()) {
            return;
        }

        int index = -1;


        index = findWinningMove("O");


        if (index == -1) {
            index = findWinningMove("X");
        }


        if (index == -1 && gamePanel.getJbutton(4).getText().equals("")) {
            index = 4;
        }


        if (index == -1) {
            index = getRandomEmptyIndex();
        }

        gamePanel.getJbutton(index).setForeground(new Color(50, 50, 70));
        gamePanel.getJbutton(index).setText("O");
        gamePanel.getLabel().setText("Player's turn (X)");
        xTurn = true; // Toggle the turn
        checkWin();
    }

    private int findWinningMove(String symbol) {

        for (int i = 0; i < 9; i += 3) {
            if (gamePanel.getJbutton(i).getText().equals(symbol) && gamePanel.getJbutton(i+1).getText().equals(symbol) && gamePanel.getJbutton(i+2).getText().equals("")) {
                return i + 2;
            } else if (gamePanel.getJbutton(i).getText().equals(symbol) && gamePanel.getJbutton(i+2).getText().equals(symbol) && gamePanel.getJbutton(i+1).getText().equals("")) {
                return i + 1;
            } else if (gamePanel.getJbutton(i+1).getText().equals(symbol) && gamePanel.getJbutton(i+2).getText().equals(symbol) && gamePanel.getJbutton(i).getText().equals("")) {
                return i;
            }
        }


        for (int i = 0; i < 3; i++) {
            if (gamePanel.getJbutton(i).getText().equals(symbol) && gamePanel.getJbutton(i+3).getText().equals(symbol) && gamePanel.getJbutton(6).getText().equals("")) {
                return i + 6;
            } else if (gamePanel.getJbutton(i).getText().equals(symbol) && gamePanel.getJbutton(i+6).getText().equals(symbol) && gamePanel.getJbutton(i+3).getText().equals("")) {
                return i + 3;
            } else if (gamePanel.getJbutton(i+3).getText().equals(symbol) && gamePanel.getJbutton(i+6).getText().equals(symbol) && gamePanel.getJbutton(i).getText().equals("")) {
                return i;
            }
        }


        if (gamePanel.getJbutton(0).getText().equals(symbol) && gamePanel.getJbutton(4).getText().equals(symbol) && gamePanel.getJbutton(8).getText().equals("")) {
            return 8;
        } else if (gamePanel.getJbutton(0).getText().equals(symbol) && gamePanel.getJbutton(8).getText().equals(symbol) && gamePanel.getJbutton(4).getText().equals("")) {
            return 4;
        } else if (gamePanel.getJbutton(4).getText().equals(symbol) && gamePanel.getJbutton(8).getText().equals(symbol) && gamePanel.getJbutton(0).getText().equals("")) {
            return 0;
        } else if (gamePanel.getJbutton(2).getText().equals(symbol) && gamePanel.getJbutton(4).getText().equals(symbol) &&gamePanel.getJbutton(6).getText().equals("")) {
            return 6;
        } else if (gamePanel.getJbutton(2).getText().equals(symbol) &&gamePanel.getJbutton(6).getText().equals(symbol) && gamePanel.getJbutton(4).getText().equals("")) {
            return 4;
        } else if (gamePanel.getJbutton(4).getText().equals(symbol) && gamePanel.getJbutton(6).getText().equals(symbol) && gamePanel.getJbutton(2).getText().equals("")) {
            return 2;
        }

        return -1;
    }


    private int getRandomEmptyIndex() {
        int index;
        do {
            index = randomNumber.nextInt(9);
        } while (!gamePanel.getJbutton(index).getText().equals(""));
        return index;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 9; i++) {
            if (gamePanel.getJbutton(i).getText().equals("")) {
                return false;
            }
        }
        return true;
    }

    public void checkWin(){
        if(gamePanel.getJbutton(0).getText() == "X" &&
                gamePanel.getJbutton(1).getText() == "X" &&
                gamePanel.getJbutton(2).getText() == "X"){
            xWins(0,1,2);
        }
        else if(gamePanel.getJbutton(3).getText() == "X" &&
                gamePanel.getJbutton(4).getText() == "X" &&
                gamePanel.getJbutton(5).getText() == "X"){
            xWins(3,4,5);
        }
        else if(gamePanel.getJbutton(6).getText() == "X" &&
                gamePanel.getJbutton(7).getText() == "X" &&
                gamePanel.getJbutton(8).getText() == "X"){
            xWins(6,7,8);
        }
        else if(gamePanel.getJbutton(0).getText() == "X" &&
                gamePanel.getJbutton(3).getText() == "X" &&
                gamePanel.getJbutton(6).getText() == "X"){
            xWins(0,3,6);
        }
        else if(gamePanel.getJbutton(1).getText() == "X" &&
                gamePanel.getJbutton(4).getText() == "X" &&
                gamePanel.getJbutton(7).getText() == "X"){
            xWins(1,4,7);
        }
        else if(gamePanel.getJbutton(2).getText() == "X" &&
                gamePanel.getJbutton(5).getText() == "X" &&
                gamePanel.getJbutton(8).getText() == "X"){
            xWins(2,5,8);
        }
        else if(gamePanel.getJbutton(2).getText() == "X" &&
                gamePanel.getJbutton(4).getText() == "X" &&
                gamePanel.getJbutton(6).getText() == "X"){
            xWins(2,4,6);
        }
        else if(gamePanel.getJbutton(0).getText() == "X" &&
                gamePanel.getJbutton(4).getText() == "X" &&
                gamePanel.getJbutton(8).getText() == "X"){
            xWins(0,4,8);
        }
        else if(gamePanel.getJbutton(0).getText() == "O" &&
                gamePanel.getJbutton(1).getText() == "O" &&
                gamePanel.getJbutton(2).getText() == "O"){
            oWins(0,1,2);
        }
        else if(gamePanel.getJbutton(3).getText() == "O" &&
                gamePanel.getJbutton(4).getText() == "O" &&
                gamePanel.getJbutton(5).getText() == "O"){
            oWins(3,4,5);
        }
        else if(gamePanel.getJbutton(6).getText() == "O" &&
                gamePanel.getJbutton(7).getText() == "O" &&
                gamePanel.getJbutton(8).getText() == "O"){
            oWins(6,7,8);
        }
        else if(gamePanel.getJbutton(0).getText() == "O" &&
                gamePanel.getJbutton(3).getText() == "O" &&
                gamePanel.getJbutton(6).getText() == "O"){
            oWins(0,3,6);
        }
        else if(gamePanel.getJbutton(1).getText() == "O" &&
                gamePanel.getJbutton(4).getText() == "O" &&
                gamePanel.getJbutton(7).getText() == "O"){
            oWins(1,4,7);
        }
        else if(gamePanel.getJbutton(2).getText() == "O" &&
                gamePanel.getJbutton(5).getText() == "O" &&
                gamePanel.getJbutton(8).getText() == "O"){
            oWins(2,5,8);
        }
        else if(gamePanel.getJbutton(2).getText() == "O" &&
                gamePanel.getJbutton(4).getText() == "O" &&
                gamePanel.getJbutton(6).getText() == "O"){
            oWins(2,4,6);
        }
        else if(gamePanel.getJbutton(0).getText() == "O" &&
                gamePanel.getJbutton(4).getText() == "O" &&
                gamePanel.getJbutton(8).getText() == "O"){
            oWins(0,4,8);
        }
        else if(gamePanel.getJbutton(0).getText() != "" &&
                gamePanel.getJbutton(1).getText() != "" &&
                gamePanel.getJbutton(2).getText() != "" &&
                gamePanel.getJbutton(3).getText() != "" &&
                gamePanel.getJbutton(4).getText() != "" &&
                gamePanel.getJbutton(5).getText() != "" &&
                gamePanel.getJbutton(6).getText() != "" &&
                gamePanel.getJbutton(7).getText() != "" &&
                gamePanel.getJbutton(8).getText() != ""){
            remis();
        }
    }
    public void remis(){
        for(int i = 0; i < 9; i++){
            gamePanel.getJbutton(i).setEnabled(false);
        }
        gamePanel.getLabel().setText("REMIS");
        resetFrame.setVisible(true);
    }
    public void xWins(int a, int b, int c){
        gamePanel.getJbutton(a).setBackground(new Color(0,225,0));
        gamePanel.getJbutton(b).setBackground(new Color(0,225,0));
        gamePanel.getJbutton(c).setBackground(new Color(0,225,0));
        gamePanel.getLabel().setText("X WINS!");
        gamePanel.getLabel().setBackground(Color.GREEN);
        resetFrame.setVisible(true);
        for(int i = 0; i < 9; i++){
            gamePanel.getJbutton(i).setEnabled(false);
        }

        resetFrame.setVisible(true);
    }

    public void oWins(int a, int b, int c){
        gamePanel.getJbutton(a).setBackground(new Color(0,225,0));
        gamePanel.getJbutton(b).setBackground(new Color(0,225,0));
        gamePanel.getJbutton(c).setBackground(new Color(0,225,0));
        gamePanel.getLabel().setText("O WINS!");
        gamePanel.getLabel().setBackground(Color.GREEN);
        resetFrame.setVisible(true);
        for(int i = 0; i < 9; i++){
            gamePanel.getJbutton(i).setEnabled(false);
        }
    }
}
