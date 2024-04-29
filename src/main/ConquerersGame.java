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
  private int bulletCount = 0;

  public ConquerersGame()
  {
    super(100);
    
    spaceshipBulletPool = new ArrayList<SpaceshipBullet>();

    setBackground(BACKGROUND_COLOR);
    
    SpaceshipSprite spaceship = new SpaceshipSprite(371, 600, spaceshipBulletPool);
    spaceship.setScale(0.25);
    addKeyListener(spaceship);

    ArrayList<AlienSprite> aliens = new ArrayList<>();
    for (int i = 0; i < 5; i++)
    {
      for (int j = 0; j < 4; j++)
      {
        AlienSprite alien = new AlienSprite(i, j, (i == 0 && j == 0));
        alien.setScale(0.25);
        aliens.add(alien);
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
    
    if (spaceshipBulletPool.size() > bulletCount) {
      for (int i = bulletCount; i < spaceshipBulletPool.size(); i++) {
        spaceshipBulletPool.get(i).setScale(0.25);
        add(spaceshipBulletPool.get(i));
      }
    }
    for (SpaceshipBullet bullet : spaceshipBulletPool) {
      bullet.handleTick(time);
    }
  }

}
