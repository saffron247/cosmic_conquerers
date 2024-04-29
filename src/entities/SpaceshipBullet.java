package entities;

import io.ResourceFinder;
import visual.dynamic.described.AbstractSprite;
import visual.statik.TransformableContent;
import visual.statik.sampled.ContentFactory;

public class SpaceshipBullet extends AbstractSprite
{
  private static final double SPEED = 30.0;
  private int timeAlive;
  private final ContentFactory contentFactory;
  private TransformableContent content;
  private double x;
  private double y;

  public SpaceshipBullet(double x, double y) {
    this.x = x;
    this.y = y;
    
    this.timeAlive = 0;

    ResourceFinder finder = ResourceFinder.createInstance(new resources.Marker());
    this.contentFactory = new ContentFactory(finder);
    TransformableContent content = contentFactory.createContent("line-bullet.png");
    this.content = content;
    
    setLocation(x, y);
    
    setVisible(true);
  }

  @Override
  protected TransformableContent getContent()
  {
    return content;
  }
  
  public int getTimeAlive()
  {
    return timeAlive;
  }

  @Override
  public void handleTick(int time)
  {
    y -= SPEED;
    timeAlive += 1;
    setLocation(x, y);
  }
}
