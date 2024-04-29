package entities;

import main.ConquerersGame;
import visual.dynamic.described.RuleBasedSprite;
import visual.dynamic.described.Sprite;
import visual.statik.TransformableContent;

public class AlienBullet extends RuleBasedSprite
{
  private static final double SPEED = 20.0;
  public boolean isAlive = true;
  public boolean isNew = true;
  private double y;

  public AlienBullet(TransformableContent content, double x, double y) {
    super(content);
    
    this.x = x;
    this.y = y;
    
    setLocation(x, y);
    setVisible(true);
  }

  @Override
  public void handleTick(int time)
  {
    y += SPEED;
    setLocation(x, y);

    // Goes off-screen
    if (y >= 800) {
      isAlive = false;
    }
    
    Sprite spaceship;

    for (Sprite antagonist : antagonists)
    {
      spaceship = antagonist;
      if (intersects(spaceship))
      {
        if (ConquerersGame.aliensAlive.indexOf(spaceship) != 0)
        {
          ((SpaceshipSprite) spaceship).hit();
          System.out.println("Score:" + ConquerersGame.score);
          isAlive = false;
        }
        break;
      }
    }
    isNew = false;
  }
}
