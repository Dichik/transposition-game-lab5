import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayingState extends GameState {

    private Grid grid;

    private Integer wasChosen;
    private Integer selected;

    private boolean lost;

    @Override
    protected void init() {
        lost = false;
        selected = wasChosen = -1; // piece was not chosen, if wasChosen
        grid = new Grid();
    }

    @Override
    public void tick() {
        if(!lost) {

        }
    }

    @Override
    public void render(Graphics graphics) {
        this.drawBackground(graphics);
    }

    @Override
    public void keyPressed(int key) {
        if(selected == -1) {
            selected = 0;
            return;
        }
        if(key == KeyEvent.VK_ESCAPE) {
            Game.STATE_MANAGER.backToPrevious();
        } else if(key == KeyEvent.VK_LEFT) {
            selected--;
            if(selected < 0) selected = Grid.SIZE - 1;
        } else if(key == KeyEvent.VK_RIGHT) {
            selected = (selected + 1) % Grid.SIZE;
        } else if(key == KeyEvent.VK_ENTER) {
            if(wasChosen != -1) {
                grid.swapOnPositions(wasChosen, selected);
                wasChosen = -1;
            } else {
                wasChosen = selected;
                selected = -1;
            }
            if(grid.checkEvenNumbers() || grid.checkOddNumbers()) {
                Game.STATE_MANAGER.backToPrevious();
                // TODO message about victory and who won (Sorry, but you've lost...)
            }
            // TODO check if there is a winner
            // FIXME what to do when ENTER pressed...
        }
    }
    // TODO add history of movements

    @Override
    public void keyReleased(int key) {

    }

    private void drawBackground(Graphics graphics) {
        for(int i = 0; i < 8; ++ i) {
            graphics.setColor(Color.BLACK);
            graphics.drawRect(i * 100 + 100, 50, 100, 100);
            if(selected != i && wasChosen != i) {
                graphics.setColor(Color.GRAY);
            } else if(selected == i) {
                graphics.setColor(new Color(145, 252, 241, 179));
            } else if(wasChosen == i) {
                graphics.setColor(new Color(138, 67, 121));
            }
            graphics.fillRect(i * 100 + 100 + 1, 50 + 1, 100 - 2, 100 - 2);
            if(Grid.array[i] % 2 == 0) {
                graphics.setColor(Color.GREEN);
            } else graphics.setColor(Color.RED);

            graphics.setFont(new Font("Roboto", Font.PLAIN, 25));
            FontMetrics fm = graphics.getFontMetrics();
            graphics.drawString(String.valueOf(Grid.array[i]), 100 * i + 150 -
                    fm.stringWidth(String.valueOf(Grid.array[i])) / 2, 100);
        }
    }

}
