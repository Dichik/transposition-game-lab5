import java.awt.*;
import java.awt.event.KeyEvent;

public class PauseMenu extends GameState {

    private String[] options;
    private Integer selected;

    @Override
    protected void init() {
        this.options = new String[]{"Return to game", "Go to menu"};
        this.selected = 0;
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
            if (selected < 0) selected = options.length - 1;
        } else if (key == KeyEvent.VK_DOWN) {
            selected = (selected + 1) % options.length;
        } else if (key == KeyEvent.VK_ENTER) {
            if (selected == 0) {
                Game.STATE_MANAGER.backToPrevious();
            } else if (selected == 1) {
                Game.STATE_MANAGER.clear();
                Game.STATE_MANAGER.changeState(new MainMenu());
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
        Level.drawingOptions(graphics, this.options, this.selected);
    }

}
