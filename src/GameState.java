import java.awt.*;

public abstract class GameState {

    public GameState() {
        this.init();
    }

    protected abstract void init();

    public abstract void tick();

    public abstract void render(Graphics graphics);

    public abstract void keyPressed(int key);

    public abstract void keyReleased(int key);

}
