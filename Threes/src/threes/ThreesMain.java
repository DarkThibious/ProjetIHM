package threes;

import javax.swing.*;

import java.awt.Dimension;
import java.util.ArrayList;

public class ThreesMain extends JFrame
{
	private JPanel contentPane;
    private StartPanel startpanel;
    private GamePanel gamepanel;
	private ArrayList<ThreesView> views = new ArrayList<ThreesView>();
    private ThreesModel model;

    public void init() 
    {
    	contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

        startpanel = new StartPanel(this);
        contentPane.add(startpanel);
        this.addView(startpanel);
        
        gamepanel = new GamePanel1D(this);
        this.addView(gamepanel);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setLocation(getX() - getWidth()/2 , getY() - getHeight()/2);
        
        this.setVisible(true);
        pack();
    }

    public ThreesMain()
    {
        super("Threes");
        model = new ThreesModel();
        this.init();
    }

    public void addView(ThreesView view)
    {
        views.add(view);
    }

    public void removeView(ThreesView view)
    {
        views.remove(view);
    }

    public void updateAllViews()
    {
        for(ThreesView view : this.views)
        {
            view.update();
        }
    }
    
    public void initPartie()
    {
    	model.initPartie();
    	contentPane.remove(startpanel);
    	contentPane.add(gamepanel);
    	gamepanel.initPartie();
    	gamepanel.update();
    	setSize(new Dimension(400,400));
    }

    public static void main(String [] args)
    {
        new ThreesMain();
    }
    
    public void moveUp()
	{
    	model.moveUp();
    	gamepanel.update();
	}
	
	public void moveDown()
	{
		model.moveDown();
		gamepanel.update();
	}
	
	public void moveLeft()
	{
		model.moveLeft();
		gamepanel.update();
	}
	
	public void moveRight()
	{
		model.moveRight();
    	gamepanel.update();
   	}
	
	public void perdu()
	{
		gamepanel.enleverMenu();
		startpanel.update();
    	contentPane.remove(gamepanel);
		contentPane.add(startpanel);
    	pack();
	}
	
	public ThreesModel getModel()
	{
		return this.model;
	}
}