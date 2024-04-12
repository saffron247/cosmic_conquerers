package main;

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
  
  public ConquerersApplication(final String[] args)
  {
    super(WIDTH, HEIGHT);
    gameScreen = new ConquerersGame();
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
    
    // Add the game screen
    VisualizationView view = gameScreen.getView();
    view.setBounds(0, 100, WIDTH, HEIGHT - 100);
    contentPane.add(view);
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
