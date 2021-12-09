import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.stream.IntStream;

public class MainMenu extends GameState {

    protected String[] options;
    protected int selected;


    @Override
    protected void init() {
        options = new String[]{
                "StartGame",
                "Exit"
        };
        selected = 0;

        System.out.println("[MainMenu] created successfully.");
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics graphics) {
        drawBackground(graphics);
        drawOptions(graphics);
    }

    @Override
    public void keyPressed(int key) {
        if (key == KeyEvent.VK_UP) {
            selected--;
            if (selected < 0) selected = 1;
        } else if (key == KeyEvent.VK_DOWN) {
            selected = (selected + 1) % 2;
        } else if (key == KeyEvent.VK_ENTER) {
            if (selected == 0) {
                Game.STATE_MANAGER.changeState(new Level());
            } else if (selected == 1) {
                System.exit(0);
            }
        }
    }

    @Override
    public void keyReleased(int key) {

    }

    private void drawBackground(Graphics graphics) {
        Level.draw(graphics);
    }

    private void drawOptions(Graphics graphics) {
        Level.drawingOptions(graphics, this.options, selected);
    }

}
