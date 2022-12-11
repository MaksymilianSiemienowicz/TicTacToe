import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class TicTacToe implements ActionListener {
    JFrame frame = new JFrame();
    JPanel game = new JPanel();
    JPanel headers = new JPanel();
    JLabel label = new JLabel();
    JButton[] buttons = new JButton[9];
    Random randomNumber = new Random();

    JFrame resetFrame = new JFrame();
    JButton resetButton = new JButton();

    boolean xTurn;
    TicTacToe(){
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(50,25,50));
        frame.setVisible(true);

        resetFrame.setSize(200,200);
        resetFrame.add(resetButton);
        resetFrame.dispose();

        resetButton.addActionListener(this);
        resetButton.setText("reset");
        resetButton.setFont(new Font("Ink Free",Font.BOLD, 50));

        label.setBackground(new Color(0,0,0));
        label.setForeground(new Color(225,1,100));
        label.setFont(new Font("Ink Free",Font.BOLD,75));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setOpaque(true);


        headers.setLayout(new BorderLayout());
        headers.setBounds(0,0,800,100);
        headers.add(label);

        game.setLayout(new GridLayout(3,3));

        frame.add(game);
        frame.add(headers,BorderLayout.NORTH);

        firstTurn();

        for(int i = 0; i < 9; i++){
            buttons[i] = new JButton();
            game.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD,120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (xTurn) {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(225, 0, 0));
                        buttons[i].setText("X");
                        xTurn = false;
                        label.setText("O turn");

                    }
                } else {
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(50, 50, 70));
                        buttons[i].setText("O");
                        xTurn = true;
                        label.setText("X turn");
                    }

                }
            }
        }
        if(e.getSource() == resetButton){
            frame.dispose();
            resetFrame.setVisible(false);
             new TicTacToe();
        }
        checkWin();
    }

    public void firstTurn(){
        if(randomNumber.nextInt(2) == 0){
            xTurn = true;
            label.setText("X turn");
        }
        else {
            xTurn = false;
            label.setText("O turn");
        }

    }

    public void checkWin(){
        if(buttons[0].getText() == "X" &&
           buttons[1].getText() == "X" &&
           buttons[2].getText() == "X"){
            xWins(0,1,2);
        }
        else if(buttons[3].getText() == "X" &&
                buttons[4].getText() == "X" &&
                buttons[5].getText() == "X"){
            xWins(3,4,5);
        }
        else if(buttons[6].getText() == "X" &&
                buttons[7].getText() == "X" &&
                buttons[8].getText() == "X"){
            xWins(6,7,8);
        }
        else if(buttons[0].getText() == "X" &&
                buttons[3].getText() == "X" &&
                buttons[6].getText() == "X"){
            xWins(0,3,6);
        }
        else if(buttons[1].getText() == "X" &&
                buttons[4].getText() == "X" &&
                buttons[7].getText() == "X"){
            xWins(1,4,7);
        }
        else if(buttons[2].getText() == "X" &&
                buttons[5].getText() == "X" &&
                buttons[8].getText() == "X"){
            xWins(2,5,8);
        }
        else if(buttons[2].getText() == "X" &&
                buttons[4].getText() == "X" &&
                buttons[6].getText() == "X"){
            xWins(2,4,6);
        }
        else if(buttons[0].getText() == "X" &&
                buttons[4].getText() == "X" &&
                buttons[8].getText() == "X"){
            xWins(0,4,8);
        }
        else if(buttons[0].getText() == "O" &&
                buttons[1].getText() == "O" &&
                buttons[2].getText() == "O"){
            oWins(0,1,2);
        }
        else if(buttons[3].getText() == "O" &&
                buttons[4].getText() == "O" &&
                buttons[5].getText() == "O"){
            oWins(3,4,5);
        }
        else if(buttons[6].getText() == "O" &&
                buttons[7].getText() == "O" &&
                buttons[8].getText() == "O"){
            oWins(6,7,8);
        }
        else if(buttons[0].getText() == "O" &&
                buttons[3].getText() == "O" &&
                buttons[6].getText() == "O"){
            oWins(0,3,6);
        }
        else if(buttons[1].getText() == "O" &&
                buttons[4].getText() == "O" &&
                buttons[7].getText() == "O"){
            oWins(1,4,7);
        }
        else if(buttons[2].getText() == "O" &&
                buttons[5].getText() == "O" &&
                buttons[8].getText() == "O"){
            oWins(2,5,8);
        }
        else if(buttons[2].getText() == "O" &&
                buttons[4].getText() == "O" &&
                buttons[6].getText() == "O"){
            oWins(2,4,6);
        }
        else if(buttons[0].getText() == "O" &&
                buttons[4].getText() == "O" &&
                buttons[8].getText() == "O"){
            oWins(0,4,8);
        }
        else if(buttons[0].getText() != "" &&
                buttons[1].getText() != "" &&
                buttons[2].getText() != "" &&
                buttons[3].getText() != "" &&
                buttons[4].getText() != "" &&
                buttons[5].getText() != "" &&
                buttons[6].getText() != "" &&
                buttons[7].getText() != "" &&
                buttons[8].getText() != ""){
            remis();
        }
    }
    public void remis(){
        for(int i = 0; i < 9; i++){
            buttons[i].setEnabled(false);
        }
        label.setText("REMIS");
        resetFrame.setVisible(true);
    }
    public void xWins(int a, int b, int c){
        buttons[a].setBackground(new Color(0,225,0));
        buttons[b].setBackground(new Color(0,225,0));
        buttons[c].setBackground(new Color(0,225,0));
        label.setText("X WINS!");
        label.setBackground(Color.GREEN);
        resetFrame.setVisible(true);
        for(int i = 0; i < 9; i++){
            buttons[i].setEnabled(false);
        }

        resetFrame.setVisible(true);
    }

    public void oWins(int a, int b, int c){
        buttons[a].setBackground(new Color(0,225,0));
        buttons[b].setBackground(new Color(0,225,0));
        buttons[c].setBackground(new Color(0,225,0));
        label.setText("O WINS!");
        label.setBackground(Color.GREEN);
        resetFrame.setVisible(true);
        for(int i = 0; i < 9; i++){
            buttons[i].setEnabled(false);
        }
    }
}
