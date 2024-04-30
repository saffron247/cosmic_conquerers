package entities;

import java.awt.geom.Rectangle2D;

import main.ConquerersGame;
import visual.dynamic.described.RuleBasedSprite;
import visual.dynamic.described.Sprite;
import visual.statik.TransformableContent;

/**
 * Bullet shot by the spaceship.
 */
public class SpaceshipBullet extends RuleBasedSprite
{
  private static final double SPEED = 20.0;
  private boolean isAlive = true;
  private boolean isNew = true;
  private double y;

  /**
   * Constructor for the SpaceshipBullet.
   * 
   * @param content
   * @param x
   * @param y
   */
  public SpaceshipBullet(final TransformableContent content, final double x, final double y)
  {
    super(content);

    this.x = x;
    this.y = y;

    setLocation(x, y);
    setVisible(true);
  }

  /**
   * gets if is alive.
   * 
   * @return isAlive
   */
  public boolean isAlive()
  {
    return isAlive;
  }

  /**
   * Checks if the SpaceshipBullet is new.
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
    y -= SPEED;
    setLocation(x, y);

    // Goes off-screen
    if (y <= 0)
    {
      isAlive = false;
    }

    Sprite alien;

    for (Sprite antagonist : antagonists)
    {
      alien = antagonist;
      if (intersects(alien))
      {
        Rectangle2D boundRect = alien.getBounds2D(true);
        if (ConquerersGame.getAliensAlive().indexOf(alien) != 0 && boundRect.getX() != 0
            && boundRect.getY() != 0)
        {
          ((AlienSprite) alien).hit();
          isAlive = false;
        }
        break;
      }
    }
    isNew = false;
  }
}
