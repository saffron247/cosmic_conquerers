package main;

import java.awt.Color;

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
    spaceship.setScale(0.25);
    addKeyListener(spaceship);
    add(spaceship);
    
    start();
  }
  
  
}
