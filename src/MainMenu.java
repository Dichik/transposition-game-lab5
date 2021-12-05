import java.awt.*;
import java.awt.event.KeyEvent;

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
                Game.STATE_MANAGER.changeState(new PlayingState());
            } else if (selected == 1) {
                System.exit(0);
            }
        }
    }

    @Override
    public void keyReleased(int key) {

    }

    private void drawBackground(Graphics graphics) {
        graphics.setColor(new Color(54, 51, 51));
        graphics.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);

        graphics.setColor(new Color(255, 255, 255, 255));
        graphics.drawRect(350, 50, 300, 450);
        graphics.setColor(new Color(50, 47, 47, 255));
        graphics.fillRect(351, 51, 299, 449);
    }

    private void drawOptions(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Roboto", Font.PLAIN + Font.ITALIC, 25));

        // FIXME rewrite using streams

        for (int i = 0; i < options.length; ++i) {
            if (selected == i) {
                graphics.setColor(Color.GREEN);
            } else graphics.setColor(Color.WHITE);
            drawCenteredString(options[i], i, graphics);
        }

    }

    private void drawCenteredString(String option, int diffBetweenLines, Graphics graphics) {
        FontMetrics fm = graphics.getFontMetrics();
        int x = (Window.WIDTH - fm.stringWidth(option)) / 2;
        int y = (fm.getAscent() + (200 + 200 * diffBetweenLines - (fm.getAscent() + fm.getDescent())) / 2);
        graphics.drawString(option, x, y);
    }

}
