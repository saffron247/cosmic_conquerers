package entities;

import java.io.IOException;
import java.util.Iterator;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import main.ConquerersGame;
import visual.dynamic.described.AbstractSprite;
import visual.dynamic.described.RuleBasedSprite;
import visual.dynamic.described.Sprite;
import visual.statik.TransformableContent;

public class SpaceshipBullet extends RuleBasedSprite
{
  private static final double SPEED = 20.0;
  public static final int SPACESHIP_BULLET_TTL = 50;
  private int timeAlive;
  private double x;
  private double y;

  public SpaceshipBullet(TransformableContent content, double x, double y) {
    super(content);
    
    this.x = x;
    this.y = y;
    
    this.timeAlive = 0;
    
    setLocation(x, y);
    
    setVisible(true);
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
    
    Sprite alien;
    
    Iterator<Sprite> iterator = antagonists.iterator();
    while (iterator.hasNext())
    {
      alien = iterator.next();
      if (intersects(alien)) {
        ((AlienSprite)alien).hit();
        System.out.println(ConquerersGame.aliensPool.indexOf(alien));
        System.out.println(alien.getBounds2D(true).toString());
        if (ConquerersGame.aliensAlive.indexOf(alien) != 0) {
          timeAlive = SPACESHIP_BULLET_TTL;
        }
        break;
      }
    }
  }
}
