package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import app.JApplication;
import visual.VisualizationView;

public class ConquerersApplication extends JApplication implements ActionListener
{
  private static final int WIDTH = 800;
  private static final int HEIGHT = 800;
  private ConquerersGame gameScreen;
  private StatsScreen statsScreen;
  private OpeningAnimation openingAnimation;
  
  public ConquerersApplication(final String[] args)
  {
    super(WIDTH, HEIGHT);
    gameScreen = new ConquerersGame();
    statsScreen = new StatsScreen();
    openingAnimation = new OpeningAnimation(gameScreen, statsScreen);
  }
  
  @Override
  public void actionPerformed(ActionEvent e)
  {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void init()
  {
    // Set up the content pane
    JPanel contentPane = (JPanel)getContentPane();
    contentPane.setLayout(null);
    
    contentPane.setBackground(new Color(0, 0, 0));
    
    VisualizationView animationView = openingAnimation.getView();
    animationView.setBounds(0, 0, WIDTH, HEIGHT);
    animationView.addKeyListener(openingAnimation);
    contentPane.add(animationView);
    
    // Add the game screen
    VisualizationView gameView = gameScreen.getView();
    gameView.setBounds(0, 100, WIDTH, HEIGHT - 100);
    gameView.setVisible(false);
    contentPane.add(gameView);
    
    // Add the stats screen
    VisualizationView statsView = statsScreen.getView();
    statsView.setBounds(0, 0, WIDTH, HEIGHT - 700);
    statsView.setVisible(false);
    contentPane.add(statsView);

    // Add the Ending Screen
    VisualizationView endingView = new EndingSreen(gameScreen, statsScreen).getView();
    endingView.setBounds(0, 0, WIDTH, HEIGHT);
    endingView.setVisible(false);
    contentPane.add(endingView);
  }
  
  /**
   * Main method. Executes on startup.
   * 
   * @param args Arguments
   */
  public static void main(final String[] args)
  {
    JApplication app = new ConquerersApplication(args);
    invokeInEventDispatchThread(app);
  }
}
