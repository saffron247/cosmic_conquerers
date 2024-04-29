package entities;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import io.ResourceFinder;
import main.ConquerersGame;
import visual.dynamic.described.AbstractSprite;
import visual.statik.TransformableContent;
import visual.statik.sampled.ContentFactory;

/**
 * Alien class.
 */
public class AlienSprite extends AbstractSprite
{

  private static final int WIDTH = 150;
  private static final int HEIGHT = 75;
  private static boolean goRight = true;
  private static boolean goDown = false;
  private static double rightest = Double.NEGATIVE_INFINITY;
  private static double leftest = Double.POSITIVE_INFINITY;
  private static boolean setMoveChange = false;
  private int damage;
  public TransformableContent content;
  private ResourceFinder finder;
  private InputStream is;
  private Sequence seq;
  private Sequencer sequencer;
  private double x;
  private double y;
  private int tickCount = 0;
  private int tickStep = 4;
  private static int movement = 10;
  private boolean last;
  private ContentFactory contentFactory;
  private int row;

  /**
   * Alien constructor.
   * 
   * @param row
   *          Row
   * @param col
   *          Col
   * @param last
   *          Last
   */
  public AlienSprite(final int row, final int col, final boolean last)
  {
    super();
    damage = 5 - row;
    this.last = last;
    this.row = row;

    finder = ResourceFinder.createInstance(new resources.Marker());
    contentFactory = new ContentFactory(finder);
    content = contentFactory.createContent("alien" + row + "1.png");
    this.x = col * WIDTH + 160;
    this.y = row * HEIGHT - 75;
    setLocation(x, y);
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
      ConquerersGame.aliensAlive.remove(this);
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
      content = contentFactory.createContent("alien" + row + "2.png");
    }
    else
    {
      content = contentFactory.createContent("alien" + row + "1.png");
    }

    tickCount++;

  }

}
