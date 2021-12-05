import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window implements ActionListener {

    public static Integer WIDTH = 1000;
    public static Integer HEIGHT = 600;
    public static JFrame window;

    public static void create() {
        window = new JFrame("Transposition");
        window.setBounds(500, 200, WIDTH, HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(new GameScreen());
        window.setVisible(true);

        System.out.println("[Window] created successfully.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
