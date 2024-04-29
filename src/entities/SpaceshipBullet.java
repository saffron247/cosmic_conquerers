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
  public boolean isAlive = true;
  public boolean isNew = true;
  private double y;

  public SpaceshipBullet(TransformableContent content, double x, double y) {
    super(content);
    
    this.x = x;
    this.y = y;
    
    setLocation(x, y);
    setVisible(true);
  }

  @Override
  public void handleTick(int time)
  {
    y -= SPEED;
    setLocation(x, y);

    // Goes off-screen
    if (y <= 0) {
      isAlive = false;
    }
    
    Sprite alien;

    for (Sprite antagonist : antagonists)
    {
      alien = antagonist;
      if (intersects(alien))
      {
        if (ConquerersGame.aliensAlive.indexOf(alien) != 0)
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
