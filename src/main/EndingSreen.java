package main;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import app.JApplication;
import visual.dynamic.sampled.Screen;

/**
 * Displays the ending screen.
 */
public class EndingSreen extends Screen implements KeyListener
{

  private ArcadeFont score;
  private ConquerersGame gameScreen;
  private StatsScreen statsScreen;

  /**
   * Constructor for the ending screen.
   * 
   * @param game
   *          the game screen
   * @param stats
   *          the stats screen
   */
  public EndingSreen(final ConquerersGame game, final StatsScreen stats)
  {
    super(16);
    gameScreen = game;
    statsScreen = stats;

    gameScreen.getView().setVisible(false);
    statsScreen.getView().setVisible(false);

    setBackground(new Color(0, 0, 0));

    score = new ArcadeFont(new Color(255, 255, 255),
        "GAME   OVER\n" + "FINAL   SCORE:   " + stats.getScore(), 40, 255, 510);
    add(score);

    start();
  }

  @Override
  public void keyPressed(final KeyEvent arg0)
  {
    
    // press space to restart or close
    if (arg0.getKeyCode() == KeyEvent.VK_SPACE)
    {
      gameScreen.getView().setVisible(true);
      statsScreen.getView().setVisible(true);
      getView().setVisible(false);
      gameScreen.start();
    }
    else
    {
      System.exit(0);
    }
  }

  @Override
  public void keyReleased(final KeyEvent arg0)
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
  }

  @Override
  public void keyTyped(final KeyEvent arg0)
  {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
  }

}
