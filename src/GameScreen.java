import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameScreen extends JPanel {

    public GameScreen() {
        super();
        this.setFocusable(true);
        this.addKeyListener(new Keyboard());

        System.out.println("[GameScreen]: Created GameScreen successfully.");
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if(Game.isRunning()) {
            Game.STATE_MANAGER.render(graphics);
        }
        super.repaint();
    }

    private class Keyboard implements KeyListener {

        @Override
        public void keyPressed(KeyEvent key) {
            if(Game.isRunning()) {
                Game.STATE_MANAGER.keyPressed(key.getKeyCode());
            }
        }

        @Override
        public void keyReleased(KeyEvent key) {
            if(Game.isRunning()) {
                Game.STATE_MANAGER.keyReleased(key.getKeyCode());
            }
        }

        @Override
        public void keyTyped(KeyEvent key) {}

    }

}
