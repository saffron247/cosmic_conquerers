package entities;

import java.io.IOException;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import io.ResourceFinder;
import main.ConquerersApplication;
import main.ConquerersGame;
import visual.dynamic.described.AbstractSprite;
import visual.statik.TransformableContent;
import visual.statik.sampled.ContentFactory;
import java.util.Random;

/**
 * Alien class.
 */
public class AlienSprite extends AbstractSprite
{

  private static int movement = 10;
  private static final int WIDTH = 150;
  private static final int HEIGHT = 75;
  private static boolean goRight = true;
  private static boolean goDown = false;
  private static final String ALIEN = "alien";
  private static final String PNG = "1.png";
  private static double rightest = Double.NEGATIVE_INFINITY;
  private static double leftest = Double.POSITIVE_INFINITY;
  private final Random rand;
  private int damage;
  private TransformableContent content;
  private ResourceFinder finder;
  private double x;
  private double y;
  private int tickCount = 0;
  private int tickStep = 4;
  private boolean last;
  private ContentFactory contentFactory;
  private int row;
  private List<AlienBullet> bulletPool;
  private boolean isAlive = true;

  /**
   * Alien constructor.
   * 
   * @param row
   *          Row
   * @param col
   *          Col
   * @param last
   *          Last
   * @param bulletPool
   *         Bullet pool
   */
  public AlienSprite(final int row, final int col, final boolean last, 
      final List<AlienBullet> bulletPool)
  {
    super();
    damage = 5 - row;
    this.last = last;
    this.row = row;

    finder = ResourceFinder.createInstance(new resources.Marker());
    contentFactory = new ContentFactory(finder);
    content = contentFactory.createContent(ALIEN + row + PNG);
    this.x = col * WIDTH + 160;
    this.y = row * HEIGHT - 75;
    setLocation(x, y);
    setVisible(true);
    this.rand = new Random();
    this.bulletPool = bulletPool;
  }

  /**
   * Gets hit.
   * 
   * @throws IOException
   * @throws InvalidMidiDataException
   * @throws MidiUnavailableException
   */
  public void hit()
  {
    damage--;
    if (damage <= 0)
    {
      setVisible(false);
      isAlive = false;
      ConquerersGame.getAliensAlive().remove(this);
    }
    ConquerersApplication.getStatScreen().changeScore(100);
  }

  @Override
  protected TransformableContent getContent()
  {
    return content;
  }

  @Override
  public void handleTick(final int arg0)
  {
    // Requires move
    if (tickCount % tickStep == 0)
    {
      if (goRight)
      {
        x += movement;
      }
      else
      {
        x -= movement;
      }
      if (goDown)
      {
        y += movement;
        if (last)
        {
          goDown = false;
        }
      }

      // Setting overall bounds of aliens
      rightest = Math.max(x, rightest);
      leftest = Math.min(x, leftest);
      setLocation(x, y);
    }
    // Gets to bottom aka lose
    if (last && y == 500)
    {
      ConquerersApplication.gameOver(false);
    }
    // Hits right wall
    if (last && rightest >= 725)
    {
      goRight = false;
      goDown = true;
      rightest = Double.NEGATIVE_INFINITY;
      // Hits left wall
    }
    else if (last && leftest <= 25)
    {
      goRight = true;
      goDown = true;
      leftest = Double.POSITIVE_INFINITY;
    }

    // changes the content
    if (tickCount % 8 < 4)
    {
      content = contentFactory.createContent(ALIEN + row + "2.png");
    }
    else
    {
      content = contentFactory.createContent(ALIEN + row + PNG);
    }

    if (isAlive && ConquerersGame.getAliensAlive().indexOf(this) != 0 && rand.nextInt(100) == 1)
    {
      content = contentFactory.createContent("ziggy-bullet.png");
      AlienBullet bullet = new AlienBullet(content, x + 25, y + 20,
          ConquerersGame.getAliensAlive().indexOf(this));
      bulletPool.add(bullet);
    }

    tickCount++;

  }

}
