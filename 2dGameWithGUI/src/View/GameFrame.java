package View;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame(GamePanel panel){
        setTitle("2d Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        add(panel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
