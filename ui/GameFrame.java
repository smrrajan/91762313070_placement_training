package ui;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

    public GameFrame() {

        add(new GamePanel());

        setTitle("Professional Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }
}