package entities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import io.ResourceFinder;
import visual.dynamic.described.AbstractSprite;
import visual.statik.sampled.ContentFactory;
import visual.statik.TransformableContent;

public class SpaceshipSprite extends AbstractSprite implements KeyListener
{
  private static final double SPEED = 30.0;
  private boolean leftHeld;
  private boolean rightHeld;
  private boolean spaceHeld;
  private boolean color = false;
  private ContentFactory contentFactory;
  private double x;
  private double y;
  private TransformableContent content;
  
  public SpaceshipSprite(double x, double y)
  {
    super();
    
    this.x = x;
    this.y = y;
    leftHeld = false;
    rightHeld = false;
    
    ResourceFinder finder = ResourceFinder.createInstance(new resources.Marker());
    this.contentFactory = new ContentFactory(finder);
    TransformableContent content = contentFactory.createContent("spaceship1.png");
    
    this.content = content;
    
    setLocation(x, y);
    
    setVisible(true);
  }

  @Override
  protected TransformableContent getContent()
  {
    return content;
  }

  @Override
  public void handleTick(int time)
  {
    // make sure the dude doesn't run off the screen
    if (leftHeld && x > 0) {
      x -= SPEED;
    }
    // 750 came from (screen width - dude width)
    if (rightHeld && x < 750) {
      x += SPEED;
    }
    setLocation(x, y);
    // shoot
    if (spaceHeld) {content = contentFactory.createContent("spaceship" + (color ? "1" : "2") +".png");
      spaceHeld = false;
      color = !color;
    }
  }

  @Override
  public void keyTyped(KeyEvent e)
  {
    // TODO Auto-generated method stub
  }

  @Override
  public void keyPressed(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      leftHeld = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      rightHeld = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      spaceHeld = true;
    }
  }

  @Override
  public void keyReleased(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      leftHeld = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      rightHeld = false;
    }
  }
}
