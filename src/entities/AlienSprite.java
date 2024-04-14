package entities;

import io.ResourceFinder;
import visual.dynamic.described.AbstractSprite;
import visual.statik.TransformableContent;
import visual.statik.sampled.ContentFactory;

/**
 * Alien class.
 */
public class AlienSprite extends AbstractSprite
{

  private static final int WIDTH = 200;
  private static final int HEIGHT = 200;
  private int damage;
  private TransformableContent content;

  /**
   * Alien constructor.
   * 
   * @param row
   *          Row
   * @param col
   *          Col
   */
  public AlienSprite(final int row, final int col)
  {
    super();
    damage = 5 - row;

    ResourceFinder finder = ResourceFinder.createInstance(new resources.Marker());
    ContentFactory contentFactory = new ContentFactory(finder);
    content = contentFactory.createContent("alien" + row + ".png");


    setLocation(col * WIDTH, row * HEIGHT - 200);
    setVisible(true);

  }

  /**
   * Get the width.
   * 
   * @return Width
   */
  public int getWidth()
  {
    return WIDTH;
  }

  /**
   * Get the height.
   * 
   * @return Height
   */
  public int getHeight()
  {
    return HEIGHT;
  }

  /**
   * Get the damage.
   * 
   * @return Damage
   */
  public int getDamage()
  {
    return damage;
  }

  /**
   * Gets hit.
   */
  public void hit()
  {
    damage--;
    if (damage <= 0)
    {
      setVisible(false);
    }
  }

  @Override
  protected TransformableContent getContent()
  {
    return content;
  }

  @Override
  public void handleTick(final int arg0)
  {
    // TODO Collision detection
  }

}
