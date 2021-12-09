import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.stream.IntStream;

public class Level extends GameState {

    private String[] options;
    private Integer selected;

    private LevelType levelType;

    @Override
    protected void init() {
        this.options = new String[]{
                "Easy",
                "Medium",
                "Hard"
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
                levelType = LevelType.EASY;
            } else if(selected == 1) {
                levelType = LevelType.MEDIUM;
            } else {
                levelType = LevelType.HARD;
            }
            Game.STATE_MANAGER.changeState(new Turn(levelType));
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

    static void drawingOptions(Graphics graphics, String[] options, Integer selected) {
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Roboto", Font.PLAIN + Font.ITALIC, 25));

        IntStream.range(0, options.length).forEachOrdered(i -> {
            if (selected == i) {
                graphics.setColor(Color.GREEN);
            } else graphics.setColor(Color.WHITE);
            drawCenteredString(options[i], i, graphics);
        });
    }

    private void drawOptions(Graphics graphics) {
        drawingOptions(graphics, this.options, selected);
    }


    private static void drawCenteredString(String option, int diffBetweenLines, Graphics graphics) {
        drawString(option, diffBetweenLines, graphics);
    }

    static void drawString(String option, int diffBetweenLines, Graphics graphics) {
        FontMetrics fm = graphics.getFontMetrics();
        int x = (Window.WIDTH - fm.stringWidth(option)) / 2;
        int y = (fm.getAscent() + (200 + 200 * diffBetweenLines - (fm.getAscent() + fm.getDescent())) / 2);
        graphics.drawString(option, x, y);
    }

    private void drawBackground(Graphics graphics) {
        draw(graphics);
    }

    static void draw(Graphics graphics) {
        graphics.setColor(new Color(54, 51, 51));
        graphics.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);

        graphics.setColor(new Color(255, 255, 255, 255));
        graphics.drawRect(350, 50, 300, 450);
        graphics.setColor(new Color(50, 47, 47, 255));
        graphics.fillRect(351, 51, 299, 449);
    }

}
