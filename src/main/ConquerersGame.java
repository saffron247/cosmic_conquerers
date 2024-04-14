package main;

import java.awt.Color;

import entities.AlienSprite;
import entities.SpaceshipSprite;
import visual.dynamic.described.Stage;

public class ConquerersGame extends Stage
{
  private static final Color BACKGROUND_COLOR = new Color(0, 0, 0);

  public ConquerersGame()
  {
    super(100);

    setBackground(BACKGROUND_COLOR);

    SpaceshipSprite spaceship = new SpaceshipSprite(300, 500);
    addKeyListener(spaceship);
    for (int i = 0; i < 5; i++)
    {
      for (int j = 0; j < 4; j++)
      {
        AlienSprite alien = new AlienSprite(i, j);
        add(alien);
      }
    }
    add(spaceship);

    start();
  }

}
