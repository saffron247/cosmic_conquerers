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
public class EndingScreen extends Stage
{
  private ArcadeFont ending;
  private ConquerersGame gameScreen;
  private StatsScreen statsScreen;
  private static final Color BACKGROUND_COLOR = new Color(0, 0, 0);
  private static final Color WHITE = new Color(255,255,255);
  private static final Color GREEN = new Color(72, 208, 62);
  private int line = 40;
  private int fontSize = 50;

  /**
   * Constructor for the opening screen.
   * @param game
   * @param stats
   */
  public EndingScreen(final ConquerersGame game, final StatsScreen stats)
  {
    super(16);
    System.out.println("Ahhhhh");
    gameScreen = game;
    statsScreen = stats;
    
    setBackground(new Color(0,0,0));
    this.ending = new ArcadeFont(WHITE, "", fontSize, 20, line);
    add(ending);
    
    start();
  }

  public void ending(final boolean won) {
    ending.changeString(won ? "Nice" : "Eat Shit");
  }

}
