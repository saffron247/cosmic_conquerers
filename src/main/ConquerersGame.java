package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import entities.AlienSprite;
import entities.SpaceshipBullet;
import entities.SpaceshipSprite;
import visual.dynamic.described.Stage;

public class ConquerersGame extends Stage
{
  private static final Color BACKGROUND_COLOR = new Color(0, 0, 0);
  private List<SpaceshipBullet> spaceshipBulletPool;
  public static List<AlienSprite> aliensPool;
  public static List<AlienSprite> aliensAlive;

  public ConquerersGame()
  {
    super(100);
    
    spaceshipBulletPool = new ArrayList<SpaceshipBullet>();

    setBackground(BACKGROUND_COLOR);
    
    SpaceshipSprite spaceship = new SpaceshipSprite(371, 600, spaceshipBulletPool);
    spaceship.setScale(0.25);
    addKeyListener(spaceship);

    aliensPool = new ArrayList<AlienSprite>();
    aliensAlive = new ArrayList<AlienSprite>();
    aliensAlive.add(new AlienSprite(-1, -1, false));
    for (int i = 1; i < 5; i++)
    {
      for (int j = 0; j < 4; j++)
      {
        AlienSprite alien = new AlienSprite(i, j, (i == 1 && j == 0));
        alien.setScale(0.25);
        aliensPool.add(alien);
        aliensAlive.add(alien);
        add(alien);
      }
    }
    add(spaceship);

    start();
  }
  
  @Override
  public void handleTick(int time)
  {
    super.handleTick(time);
    
    for (SpaceshipBullet bullet : spaceshipBulletPool) {
      if (bullet.getTimeAlive() >= SpaceshipBullet.SPACESHIP_BULLET_TTL) {
        spaceshipBulletPool.remove(bullet);
        remove(bullet);
        break;
      } else {
        if (bullet.getTimeAlive() == 0) {
          bullet.setScale(0.15);
          for (AlienSprite alien : aliensAlive) {
            bullet.addAntagonist(alien);
          }
          add(bullet);
        }
        bullet.handleTick(time);
      }
    }
  }

}
