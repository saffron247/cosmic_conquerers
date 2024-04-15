package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

import io.ResourceFinder;
import visual.statik.SimpleContent;

/**
 * Creates a word Content in a pixelated font.
 */
class ArcadeFont implements SimpleContent
{
  private Font arcadeFont;
  private Color fontColor;
  private String text;
  private int x;
  private int y;
  
  /**
   * Constructor for making the font.
   */
  public ArcadeFont (final Color c, final String t, final int fontSize, final int x, final int y)
  {
    fontColor = c;
    text = t;
    this.x = x;
    this.y = y;
    
    ResourceFinder finder = ResourceFinder.createInstance(new resources.Marker());
    InputStream arcadeFontStream = finder.findInputStream("ARCADECLASSIC.TTF");
    try {
      arcadeFont = Font.createFont(Font.TRUETYPE_FONT, arcadeFontStream);
      GraphicsEnvironment ge = 
          GraphicsEnvironment.getLocalGraphicsEnvironment();
      ge.registerFont(arcadeFont);
      arcadeFont = arcadeFont.deriveFont((float)fontSize);
   } catch (IOException|FontFormatException e) {
        //Handle exception
     System.out.println("not created");
   }
  }
  
  @Override
  public void render(Graphics g)
  {
    g.setColor(fontColor);
    g.setFont(arcadeFont);
    g.drawString(text, x, y);
  }
}