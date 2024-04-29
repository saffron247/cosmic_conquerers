package entities;

import main.ConquerersGame;
import visual.dynamic.described.RuleBasedSprite;
import visual.dynamic.described.Sprite;
import visual.statik.TransformableContent;

public class AlienBullet extends RuleBasedSprite
{
  private static final double SPEED = 20.0;
  public boolean isAlive = true;
  public boolean isNew = true;
  private double y;
  private int parentInt;

  public AlienBullet(TransformableContent content, double x, double y, int parentInt) {
    super(content);
    
    this.x = x;
    this.y = y;
    this.parentInt = parentInt;
    
    setLocation(x, y);
    setVisible(true);
  }

  @Override
  public void handleTick(int time)
  {
//    for (Sprite antagonist : antagonists) {
//      System.out.println(antagonist);
//    }
    y += SPEED;
    setLocation(x, y);

    // Goes off-screen
    if (y >= 800) {
      isAlive = false;
    }

    // fun fact, now way to just query the spaceship, you have
    // to run through this loop even though we know there will
    // only ever be checking one entity. RIP performance.
    for (Sprite antagonist : antagonists)
    {
      if (intersects(antagonist))
      {
          ((SpaceshipSprite) antagonist).hit();
          System.out.println(antagonist);
          System.out.println("Hit by alien: " + this.parentInt);
          isAlive = false;
          break;
      }
    }
    isNew = false;
  }
}
