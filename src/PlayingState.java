import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayingState extends GameState {

    private Grid grid;

    private Integer wasChosen;
    private Integer selected;

    private boolean lost;

    private final LevelType levelType;
    private final Boolean youMoveFirst;

    static Boolean yourTurn;

    public PlayingState(LevelType levelType, Boolean youMoveFirst) {
        this.levelType = levelType;
        this.youMoveFirst = youMoveFirst;
    }

    @Override
    protected void init() {
        this.lost = false;
        yourTurn = true;
        this.selected = 0;
        this.wasChosen = -1; // piece was not chosen, if wasChosen
        this.grid = new Grid();
    }

    @Override
    public void tick() {
        if (!yourTurn && !lostStage()) {

            switch (levelType) {
                case EASY:
                    Grid.makeFirstPossibleMove(); break;
                case MEDIUM:
                    grid.makeMove(1); // FIXME constants
                    break;
                case HARD:
                    grid.makeMove(3);
                    break;
            }

            grid.clearComputerMoves();
            changeTurn();
        }

        if (lostStage()) {
            if (grid.checkEvenNumbers()) {
                JOptionPane.showMessageDialog(new JFrame(), "Sorry, but you lost");
            } else JOptionPane.showMessageDialog(new JFrame(), "Congrats, you've done it");

            Game.STATE_MANAGER.clear();
            Game.STATE_MANAGER.changeState(new MainMenu());
        }
    }

    private boolean lostStage() {
        return grid.checkEvenNumbers() || grid.checkOddNumbers();
    }

    @Override
    public void render(Graphics graphics) {
        this.drawBackground(graphics);
    }

    @Override
    public void keyPressed(int key) {
        if (yourTurn) {
            if (key == KeyEvent.VK_ESCAPE) {
                if(wasChosen == -1) {
                    Game.STATE_MANAGER.changeState(new PauseMenu());
                } else wasChosen = -1;
            } else if (key == KeyEvent.VK_LEFT) {
                selected--;
                if (selected < 0) selected = Grid.SIZE - 1;
            } else if (key == KeyEvent.VK_RIGHT) {
                selected = (selected + 1) % Grid.SIZE;
            } else if (key == KeyEvent.VK_ENTER) {
                if (wasChosen != -1 && Grid.checkIfWeCanMakeMove(wasChosen, selected)) {
                    Grid.swapOnPositions(wasChosen, selected);
                    wasChosen = -1;
                    changeTurn();
                } else if(selected != wasChosen) {
                    wasChosen = selected;
                }
            }
        }

//        TODO sleep for a while and mark both pieces as selected
    }
    // TODO add history of movements

    @Override
    public void keyReleased(int key) {
    }

    // TODO add ability to choose size of array [N] mod 2 == 0

    // TODO add button to retry

    // FIXME add better styles

    private void changeTurn() {
        yourTurn = !yourTurn;
    }

    private void drawBackground(Graphics graphics) {
        for (int i = 0; i < 8; ++i) {
            graphics.setColor(Color.BLACK);
            graphics.drawRect(i * 100 + 100, 50, 100, 100);
//            FIXME fix these cases

            if (!yourTurn) {
                if (grid.madeByComputer(i)) {
                    graphics.setColor(new Color(100, 100, 100));
                    graphics.fillRect(i * 100 + 100 + 1, 50 + 1, 100 - 4, 100 - 4);
                } else {
                    graphics.setColor(Color.GRAY);
                    graphics.fillRect(i * 100 + 100 + 1, 50 + 1, 100 - 2, 100 - 2);
                }
            } else {
                if (selected == i) {
                    graphics.setColor(new Color(145, 252, 241, 179));
                    graphics.fillRect(i * 100 + 100 + 1, 50 + 1, 100 - 2, 100 - 2);
                } else if (wasChosen == i) {
                    graphics.setColor(new Color(100, 100, 100));
                    graphics.fillRect(i * 100 + 100 + 1, 50 + 1, 100 - 4, 100 - 4);
                } else {
                    graphics.setColor(Color.GRAY);
                    graphics.fillRect(i * 100 + 100 + 1, 50 + 1, 100 - 2, 100 - 2);
                }
            }
//            FIXME colors - globally declared
//            FIXME different numbers - globally declared
            if (Grid.array[i] % 2 == 0) {
                graphics.setColor(Color.GREEN);
            } else graphics.setColor(Color.RED);

            graphics.setFont(new Font("Roboto", Font.PLAIN, 40));
            FontMetrics fm = graphics.getFontMetrics();
            graphics.drawString(String.valueOf(Grid.array[i]), 100 * i + 150 -
                    fm.stringWidth(String.valueOf(Grid.array[i])) / 2, 100);
        }
    }

}
