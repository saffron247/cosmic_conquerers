package entities;

import java.awt.geom.Rectangle2D;

import visual.dynamic.described.RuleBasedSprite;
import visual.statik.TransformableContent;

/**
 * Bullet shot by the aliens.
 */
public class AlienBullet extends RuleBasedSprite
{
  private static final double SPEED = 20.0;
  private boolean isAlive = true;
  private boolean isNew = true;
  private double y;

  /**
   * Constructor for the AlienBullet.
   * 
   * @param content
   * @param x
   * @param y
   * @param parentInt
   */
  public AlienBullet(final TransformableContent content, final double x, final double y,
      final int parentInt)
  {
    super(content);
    setLocation(x, y);

    this.x = x;
    this.y = y;

    setVisible(true);
  }

  /**
   * gets the status of the AlienBullet.
   * 
   * @return AlienBullet
   */
  public boolean isAlive()
  {
    return isAlive;
  }

  /**
   * Checks if the AlienBullet is new.
   * 
   * @return isNew
   */
  public boolean isNew()
  {
    return isNew;
  }

  @Override
  public void handleTick(final int time)
  {
    // for (Sprite antagonist : antagonists) {
    // System.out.println(antagonist);
    // }
    y += SPEED;
    setLocation(x, y);

    // Goes off-screen
    if (y >= 800)
    {
      isAlive = false;
    }

    // fun fact, now way to just query the spaceship, you have
    // to run through this loop even though we know there will
    // only ever be checking one entity. RIP performance.

    // ryan put the above comment earlier, but i have no idea what he's talking about;
    // i managed to get this working just by accessing index 0 of antagonists, no loop
    // required
    if (antagonists.size() > 0)
    {
      SpaceshipSprite spaceship = (SpaceshipSprite) antagonists.get(0);
      Rectangle2D boundRect = spaceship.getBounds2D(true);
      if (intersects(spaceship) && boundRect.getX() != 0 && boundRect.getY() != 0)
      {
        spaceship.hit();
        // System.out.println(spaceship);
        // System.out.println("Hit by alien: " + this.parentInt);
        // System.out.println(spaceship.getBounds2D());
        // System.out.println(this.getBounds2D());
        isAlive = false;
      }
    }
    isNew = false;
  }
}
