import javax.swing.*;

public class Window {

    private static Integer WIDTH = 1000;
    private static Integer HEIGHT = 600;

    public static void main(String[] args) {
        JFrame window = new JFrame("Transposition");
        window.setBounds(500, 200, WIDTH, HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        window.setVisible(true);
    }
}
