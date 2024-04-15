package main;

import java.awt.Color;
import java.util.ArrayList;

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

    SpaceshipSprite spaceship = new SpaceshipSprite(375, 600);
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

}
