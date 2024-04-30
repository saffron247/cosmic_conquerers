package main;

import visual.dynamic.described.Stage;

import java.awt.*;

/**
 * Displays the opening screen, and when the spacebar is pressed, the game appears.
 */
public class EndingScreen extends Stage
{
  private static final Color BACKGROUND_COLOR = new Color(0, 0, 0);
  private static final Color GREEN = new Color(72, 208, 62);
  private ArcadeFont ending;
  private ArcadeFont score;
  private StatsScreen statsScreen;


  /**
   * Constructor for the opening screen.
   * 
   * @param stats The stats screen
   */
  public EndingScreen(final StatsScreen stats)
  {
    super(16);
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

}
