import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// FIXME add pre-commit, and other stuff
// TODO exe file to run the game
public class Game {

    public static final GameStateManager STATE_MANAGER = new GameStateManager();
    private static boolean running = false;
    private static Timer timer;

    public static void main(String[] args) {
        Window.create();
        startGame();
    }

//    TODO move all classes in packages

    private static void startGame() {
        System.out.println("[Game] starting...");

        STATE_MANAGER.changeState(new MainMenu());
        timer = new Timer(20, new GameLoop());
        running = true;
        timer.start();
    }

    public static boolean isRunning() {
        return running;
    }

    private static class GameLoop implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Game.STATE_MANAGER.tick();
        }

    }

}
