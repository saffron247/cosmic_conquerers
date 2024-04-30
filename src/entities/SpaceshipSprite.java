package entities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import io.ResourceFinder;
import main.ConquerersApplication;
import visual.dynamic.described.AbstractSprite;
import visual.statik.sampled.ContentFactory;
import visual.statik.TransformableContent;

/**
 * Spaceship class.
 */
public class SpaceshipSprite extends AbstractSprite implements KeyListener
{
  private static final double SPEED = 30.0;
  private boolean leftHeld;
  private boolean rightHeld;
  private boolean color = false;
  private ContentFactory contentFactory;
  private TransformableContent content;
  private TransformableContent bulletContent;
  private List<SpaceshipBullet> bulletPool;

  /**
   * Constructor for the SpaceshipSprite.
   * 
   * @param x Starting x coordinate
   * @param y Starting y coordinate
   * @param bulletPool Pool of bullets
   */
  public SpaceshipSprite(final double x, final double y, final List<SpaceshipBullet> bulletPool)
  {
    super();

    this.x = x;
    this.y = y;
    this.bulletPool = bulletPool;

    leftHeld = false;
    rightHeld = false;

    ResourceFinder finder = ResourceFinder.createInstance(new resources.Marker());
    this.contentFactory = new ContentFactory(finder);
    content = contentFactory.createContent("spaceship1.png");

    setLocation(x, y);

    setVisible(true);
  }

  @Override
  protected TransformableContent getContent()
  {
    return content;
  }

  @Override
  public void handleTick(final int time)
  {
    // Prevent spaceship from going off edge of the screen
    if (leftHeld && x > 15)
    {
      x -= SPEED;
    }
    if (rightHeld && x < 690)
    {
      x += SPEED;
    }
    setLocation(x, y);
  }

  @Override
  public void keyTyped(final KeyEvent e)
  {
    // N/A
  }

  @Override
  public void keyPressed(final KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_LEFT)
    {
      leftHeld = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
    {
      rightHeld = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_SPACE && bulletPool.isEmpty())
    {
      // Change color
      content = contentFactory.createContent("spaceship" + (color ? "1" : "2") + ".png");
      color = !color;

      // Generate sound
      ResourceFinder finder = ResourceFinder.createInstance(new resources.Marker());
      InputStream is = finder.findInputStream("laser_sound.wav");

      BufferedInputStream bis = new BufferedInputStream(is);
      // Create an AudioInputStream from the InputStream
      AudioInputStream stream = null;
      try
      {
        stream = AudioSystem.getAudioInputStream(bis);
      }
      catch (UnsupportedAudioFileException exc)
      {
        exc.printStackTrace();
      }
      catch (IOException exc)
      {
        exc.printStackTrace();
      }
      // Create a Clip (i.e., a Line that can be pre-loaded)
      Clip clip = null;
      try
      {
        clip = AudioSystem.getClip();
      }
      catch (LineUnavailableException exc)
      {
        exc.printStackTrace();
      }
      // Tell the Clip to acquire any required system resources and become operational
      try
      {
        clip.open(stream);
      }
      catch (LineUnavailableException exc)
      {
        exc.printStackTrace();
      }
      catch (IOException exc)
      {
        exc.printStackTrace();
      }
      clip.start();

      // Create bullet
      bulletContent = contentFactory.createContent("line-bullet.png");
      SpaceshipBullet bullet = new SpaceshipBullet(bulletContent, x + 25, y - 50);
      bulletPool.add(bullet);
    }
  }

  @Override
  public void keyReleased(final KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_LEFT)
    {
      leftHeld = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
    {
      rightHeld = false;
    }
  }

  /**
   * Gets hit.
   */
  public void hit()
  {
    ConquerersApplication.getStatScreen().death();
    ConquerersApplication.getStatScreen().changeScore(-50);
  }
}
