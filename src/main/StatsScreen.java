package main;

import java.awt.Color;

import io.ResourceFinder;
import visual.dynamic.described.Stage;
import visual.statik.TransformableContent;
import visual.statik.sampled.ContentFactory;

/**
 * Displays the score, and the number of lives left.
 */
public class StatsScreen extends Stage
{
  
  private static final Color BACKGROUND_COLOR = new Color(0, 0, 0);
  private static final Color WHITE = new Color(255,255,255);
  private static final Color GREEN = new Color(72, 208, 62);
  private int line = 40;
  private int fontSize = 50;
  
  private ArcadeFont score;
  private int scoreNumber = 0;
  private int lifeNum;
  private TransformableContent[] lifes;
  
  /**
   * Base Constructor.
   */
  public StatsScreen()
  {
    super(100);
    setBackground(BACKGROUND_COLOR);
    
    ArcadeFont scoreWord = new ArcadeFont(WHITE, "SCORE", fontSize, 20, line);
    add(scoreWord);
    
    this.score = new ArcadeFont(GREEN, "0", fontSize, 200, line);
    add(score);
    
    ArcadeFont lives = new ArcadeFont(WHITE, "LIVES", fontSize, 490, line);
    add(lives);
    
    ResourceFinder finder = ResourceFinder.createInstance(new resources.Marker());
    ContentFactory contentFactory = new ContentFactory(finder);
    lifes = new TransformableContent[3];
    lifeNum = 3;
    
    for (int i = 0; i < 3; i++) 
    {
      lifes[i] = contentFactory.createContent("lil-guy.png");
      lifes[i].setScale(.60, .60);
      lifes[i].setLocation(650 + 50 * i, 10);
      add(lifes[i]);
    }
    
    start();
  }

  /**
   * Checks if the player is out of lives.
   * 
   * @return True if the player is out of lives; false otherwise
   */
  public boolean isFullyDead()
  {
    return lifeNum == 0;
  }
  
  /**
   * Removes a life from the screen.
   */
  public void death()
  {
    if (lifeNum > 0)
    {
      lifeNum--;
      remove(lifes[lifeNum]);
    }
  }

  /**
   * Gets the score string (for display in the ending screen).
   * 
   * @return The score string
   */
  public String getScore() 
  {
    return scoreNumber + "";
  }
  
  /**
   * Adjust the score value by a new value provided by the player.
   * 
   * @param playerScore The value to add to the score
   */
  public void changeScore(final int playerScore) 
  {
    scoreNumber = Math.max(0, scoreNumber + playerScore);
    score.changeString("" + scoreNumber);
  }
}
