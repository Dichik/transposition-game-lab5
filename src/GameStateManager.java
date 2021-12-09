import java.awt.*;
import java.util.Stack;

public class GameStateManager {

    private Stack<GameState> gameStackStates;

    public GameStateManager() {
        gameStackStates = new Stack<>();

        System.out.println("[GameState]: Created GameState successfully.");
    }

    public void changeState(GameState state) {
        gameStackStates.add(state);
    }

    public void backToPrevious() {
        gameStackStates.pop();
    }

    public void render(Graphics graphics) {
        gameStackStates.peek().render(graphics);
    }

    public void keyPressed(int key) {
        gameStackStates.peek().keyPressed(key);
    }

    public void keyReleased(int key) {
        gameStackStates.peek().keyReleased(key);
    }

    public void tick() {
        this.gameStackStates.peek().tick();
    }

    public void clear() {
        this.gameStackStates.clear();
    }
}
