import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake");

        Gameplay gameplay = new Gameplay();

        frame.setBounds(10, 10, 905, 700);
        frame.setBackground(Color.WHITE);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gameplay);
        frame.setLocation(250, 50);
        frame.setVisible(true);
    }
}
