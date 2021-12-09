import java.awt.*;
import java.awt.event.KeyEvent;

public class Turn extends GameState {

    private String[] options;
    private Integer selected;

    private LevelType levelType;
    private Boolean youMoveFirst;

    public Turn(LevelType levelType) {
        this.levelType = levelType;
    }

    @Override
    protected void init() {
        this.options = new String[]{
                "You Start",
                "Computer Starts"
        };
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
        if (key == KeyEvent.VK_ENTER) {
            if(selected == 0) {
                youMoveFirst = true;
            } else {
                youMoveFirst = false;
            }
            Game.STATE_MANAGER.changeState(new PlayingState(levelType, youMoveFirst));
        } else if (key == KeyEvent.VK_UP) {
            selected--;
            if(selected < 0) selected = options.length - 1;
        } else if (key == KeyEvent.VK_DOWN) {
            selected = (selected + 1) % options.length;
        } else if (key == KeyEvent.VK_ESCAPE) {
            Game.STATE_MANAGER.backToPrevious();
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
