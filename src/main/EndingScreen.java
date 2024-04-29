package main;

import io.ResourceFinder;
import visual.dynamic.sampled.Screen;
import visual.statik.TransformableContent;
import visual.statik.sampled.ContentFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Displays the opening screen, and when the spacebar is pressed, the game appears.
 */
public class EndingScreen extends Screen implements KeyListener
{
  private ArcadeFont lives;
  private ConquerersGame gameScreen;
  private StatsScreen statsScreen;

  /**
   * Constructor for the opening screen.
   * @param game
   * @param stats
   */
  public EndingScreen(final ConquerersGame game, final StatsScreen stats)
  {
    super(16);
    gameScreen = game;
    statsScreen = stats;
    
    setBackground(new Color(60,80,90));
    
    start();
  }

  @Override
  public void keyTyped(final KeyEvent e)
  {
  }

  @Override
  public void keyPressed(final KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      // Space key pressed
      System.out.println("Space key pressed on Screen");
      getView().setVisible(false);
      gameScreen.getView().setVisible(true);
      gameScreen.start();
      statsScreen.getView().setVisible(true);
    }
  }

  @Override
  public void keyReleased(final KeyEvent e)
  {
    // TODO Auto-generated method stub

  }
}
