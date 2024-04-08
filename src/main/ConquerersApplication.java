package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import app.JApplication;
import visual.VisualizationView;
import visual.dynamic.described.Stage;

public class ConquerersApplication extends JApplication implements ActionListener
{
  private static final Color BACKGROUND_COLOR = new Color(0, 0, 0);
  private static final int WIDTH = 800;
  private static final int HEIGHT = 800;
  private Stage stage;
  
  public ConquerersApplication(final String[] args)
  {
    super(WIDTH, HEIGHT);
    stage = new Stage(100);
    stage.setBackground(BACKGROUND_COLOR);
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
    
    VisualizationView view = stage.getView();
    view.setBounds(0, 0, WIDTH, HEIGHT);
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
