package main;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import io.ResourceFinder;
import visual.dynamic.sampled.Screen;
import visual.statik.TransformableContent;
import visual.statik.sampled.ContentFactory;

/**
 * Displays the opening screen, and when the spacebar is pressed, the game appears.
 */
public class OpeningAnimation extends Screen implements KeyListener
{
  private ArcadeFont lives;
  private ConquerersGame gameScreen;
  private StatsScreen statsScreen;
  
  /**
   * Constructor for the opening screen.
   * @param game
   * @param stats
   */
  public OpeningAnimation(final ConquerersGame game, final StatsScreen stats)
  { 
    super(16);
    gameScreen = game;
    statsScreen = stats;
    
    setBackground(new Color(0,0,0));
    ResourceFinder rf = ResourceFinder.createInstance(new resources.Marker());
    String[] names = rf.loadResourceNames("opening.txt");
    ContentFactory factory = new ContentFactory(rf);
    TransformableContent[] frames1 = factory.createContents(names, 3);
    
    for (int i = 0; i < frames1.length; i++)
    {
      add(frames1[i]);
    }
    
    lives = new ArcadeFont(new Color(255, 255, 255), "SPACE   TO   ENTER", 40, 255, 510);
    add(lives);
    
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
      statsScreen.getView().setVisible(true);
    }
  }

  @Override
  public void keyReleased(final KeyEvent e)
  {
    // TODO Auto-generated method stub
    
  }
}
