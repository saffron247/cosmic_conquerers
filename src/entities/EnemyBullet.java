package entities;

import io.ResourceFinder;
import visual.dynamic.described.AbstractSprite;
import visual.statik.TransformableContent;
import visual.statik.sampled.ContentFactory;

public class EnemyBullet extends AbstractSprite
{

  private final ContentFactory contentFactory;
  private TransformableContent content;
  private double x;
  private double y;

  public EnemyBullet(double x, double y) {
    this.x = x;
    this.y = y;

    ResourceFinder finder = ResourceFinder.createInstance(new resources.Marker());
    this.contentFactory = new ContentFactory(finder);
    TransformableContent content = contentFactory.createContent("spaceship1.png");
    this.content = content;
  }

  @Override
  protected TransformableContent getContent()
  {
    return content;
  }

  @Override
  public void handleTick(int time)
  {
    setLocation(x, y + 1);

  }
}
