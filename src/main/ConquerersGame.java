package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import entities.AlienBullet;
import entities.AlienSprite;
import entities.SpaceshipBullet;
import entities.SpaceshipSprite;
import visual.dynamic.described.Stage;

/**
 * ConquerersGame class.
 */
public class ConquerersGame extends Stage
{
  private static List<AlienSprite> aliensPool;
  private static List<AlienSprite> aliensAlive;
  private static final Color BACKGROUND_COLOR = new Color(0, 0, 0);
  private List<SpaceshipBullet> spaceshipBulletPool;
  private List<AlienBullet> alienBulletPool;
  private SpaceshipSprite spaceship;

  /**
   * Constructor for the ConquerersGame.
   */
  public ConquerersGame()
  {
    super(100);

    spaceshipBulletPool = new ArrayList<>();
    alienBulletPool = new ArrayList<>();

    setBackground(BACKGROUND_COLOR);

    this.spaceship = new SpaceshipSprite(371, 600, spaceshipBulletPool);
    spaceship.setScale(0.25);
    addKeyListener(spaceship);

    aliensPool = new ArrayList<>();
    aliensAlive = new ArrayList<>();
    aliensAlive.add(new AlienSprite(-1, -1, false, alienBulletPool));
    for (int i = 1; i < 5; i++)
    {
      for (int j = 0; j < 4; j++)
      {
        AlienSprite alien = new AlienSprite(i, j, (i == 1 && j == 0), alienBulletPool);
        alien.setScale(0.25);
        aliensPool.add(alien);
        aliensAlive.add(alien);
        add(alien);
      }
    }
    add(spaceship);
  }

  /**
   * Starts the game.
   */
  public void start()
  {
    super.start();
  }

  /**
   * Get the aliens pool.
   * 
   * @return aliensPool Pool of all aliens
   */
  public static List<AlienSprite> getAliensPool()
  {
    return aliensPool;
  }

  /**
   * Get the aliens alive.
   * 
   * @return aliensAlive Pool of living aliens
   */
  public static List<AlienSprite> getAliensAlive()
  {
    return aliensAlive;
  }

  @Override
  public void handleTick(final int time)
  {
    super.handleTick(time);

    // Spaceship Bullet Handling
    for (SpaceshipBullet bullet : spaceshipBulletPool)
    {
      if (!bullet.isAlive())
      {
        spaceshipBulletPool.remove(bullet);
        for (AlienSprite alien : aliensAlive)
        {
          bullet.removeAntagonist(alien);
        }
        remove(bullet);
        break;
      }
      else
      {
        if (bullet.isNew())
        {
          bullet.setScale(0.15);
          for (AlienSprite alien : aliensAlive)
          {
            bullet.addAntagonist(alien);
          }
          add(bullet);
        }
        bullet.handleTick(time);
      }
    }
    // Alien Bullet Handling
    for (AlienBullet bullet : alienBulletPool)
    {
      if (!bullet.isAlive())
      {
        alienBulletPool.remove(bullet);
        bullet.removeAntagonist(spaceship);
        remove(bullet);
        break;
      }
      else
      {
        if (bullet.isNew())
        {
          bullet.setScale(0.15);
          bullet.addAntagonist(spaceship);
          add(bullet);
        }
        bullet.handleTick(time);
      }
    }

    // Lost the game
    if (ConquerersApplication.getStatScreen().isFullyDead())
    {
      ConquerersApplication.gameOver(false);
    }
    // Won the game
    if (aliensAlive.size() == 1)
    {
      ConquerersApplication.gameOver(true);
    }

  }

}
