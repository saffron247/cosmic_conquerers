package entities;

import java.awt.geom.Rectangle2D;

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
    setLocation(x, y);
    
    this.x = x;
    this.y = y;
    this.parentInt = parentInt;
    
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
    
    // ryan put the above comment earlier, but i have no idea what he's talking about;
    // i managed to get this working just by accessing index 0 of antagonists, no loop
    // required
    if (antagonists.size() > 0) {
      SpaceshipSprite spaceship = (SpaceshipSprite) antagonists.get(0);
      Rectangle2D boundRect = spaceship.getBounds2D(true);
      if (intersects(spaceship)
          && boundRect.getX() != 0
          && boundRect.getY() != 0)
      {
          spaceship.hit();
//          System.out.println(spaceship);
//          System.out.println("Hit by alien: " + this.parentInt);
//          System.out.println(spaceship.getBounds2D());
//          System.out.println(this.getBounds2D());
          isAlive = false;
      }
    }
    isNew = false;
  }
}
