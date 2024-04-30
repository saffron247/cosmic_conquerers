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

  /**
   * Constructor for the AlienBullet.
   * 
   * @param content Alien bullet content
   * @param x Location x coordinate
   * @param y Location y coordinate
   */
  public AlienBullet(final TransformableContent content, final double x, final double y)
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
    y += SPEED;
    setLocation(x, y);

    // Goes off-screen
    if (y >= 800)
    {
      isAlive = false;
    }

    if (antagonists.size() > 0)
    {
      SpaceshipSprite spaceship = (SpaceshipSprite) antagonists.get(0);
      Rectangle2D boundRect = spaceship.getBounds2D(true);
      if (intersects(spaceship) && boundRect.getX() != 0 && boundRect.getY() != 0)
      {
        spaceship.hit();
        isAlive = false;
      }
    }
    isNew = false;
  }
}
