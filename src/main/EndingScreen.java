package main;

import io.ResourceFinder;
import visual.dynamic.described.Stage;
import visual.dynamic.sampled.Screen;
import visual.statik.TransformableContent;
import visual.statik.sampled.ContentFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Displays the opening screen, and when the spacebar is pressed, the game appears.
 */
public class EndingScreen extends Stage implements KeyListener
{
  private static final Color BACKGROUND_COLOR = new Color(0, 0, 0);
  private static final Color WHITE = new Color(255, 255, 255);
  private static final Color GREEN = new Color(72, 208, 62);
  private ArcadeFont ending;
  private ArcadeFont score;
  private ConquerersGame gameScreen;
  private StatsScreen statsScreen;
  private int line = 40;
  private int fontSize = 50;

  /**
   * Constructor for the opening screen.
   * 
   * @param game
   * @param stats
   */
  public EndingScreen(final ConquerersGame game, final StatsScreen stats)
  {
    super(16);
    System.out.println("Ahhhhh");
    gameScreen = game;
    statsScreen = stats;

    setBackground(BACKGROUND_COLOR);
    this.ending = new ArcadeFont(GREEN, "", 80, 180, 300);
    this.score = new ArcadeFont(GREEN, "", 80, 70, 450);


    add(ending);
    add(score);

    start();
  }

  /**
   * Changes the string displayed on the screen.
   * 
   * @param won
   *          true if the player won, false if the player lost
   */
  public void ending(final boolean won)
  {
    ending.changeString(won ? "YOU   WIN!" : "GAME   OVER");
    score.changeString("FINAL   SCORE   " + statsScreen.getScore());
  }

  // if space restart the game, enter quit
  @Override
  public void keyPressed(final KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_SPACE)
    {
      new ConquerersGame();
    }
    else if (e.getKeyCode() == KeyEvent.VK_ENTER)
    {
      System.exit(0);
    }
  }

  @Override
  public void keyTyped(KeyEvent e)
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
  }

  @Override
  public void keyReleased(KeyEvent e)
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
  }

}
