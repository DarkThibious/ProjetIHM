package threes;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setLocation(getX() - getWidth()/2 , getY() - getHeight()/2);
     // Create the menus
     		final JMenuBar menuBar = new JMenuBar();
     		final  JMenu menu1 = new JMenu("Fichier");
     		final  JMenu menu2 = new JMenu("Aide");

     		final JMenuItem item1 = new JMenuItem("Meilleurs scores");
     		final JMenuItem item2 = new JMenuItem("Quitter");
     		final JMenuItem item3 = new JMenuItem("Consignes");
     	
     		menu1.add(item1);
     		menu1.add(item2);
     		menu2.add(item3);
     		menuBar.add(menu1);
     		menuBar.add(menu2);
     		setJMenuBar(menuBar);
     		
     		ActionListener menuListener = new ActionListener()
     		{
     			@Override
     		    public void actionPerformed(ActionEvent event)
     			{
     				JMenuItem menuListener = (JMenuItem) event.getSource();
     			
     				if(menuListener==item1)
     				{
     					// ouvrir une pop up avec les meilleurs scores
     					JOptionPane.showMessageDialog(contentPane, "Meilleurs scores ");
     				}
     				else if(menuListener==item2)
     				{
     					// fermer la fenetre    
     					dispose();   
     					
     				}
     				else if(menuListener==item3)
     				{
     					// ouvrir une pop up avec l'aide
     					 JOptionPane.showMessageDialog(contentPane, "Déplacez les tuiles (clavier,souris,menu) afin de faire rencontrer les multiples de 3 ou les '2' et les '1' ");
     				}
     			}
     		    
     		   };
     			item1.addActionListener(menuListener);
     			item2.addActionListener(menuListener);
     			item3.addActionListener(menuListener);
        
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
    	if(startpanel.getInterfaceAv() == false)
    	{
    		gamepanel = new GamePanel1D(this);
    	}
    	else
    	{
    		gamepanel = new GamePanel2D(this);
    	}
        this.addView(gamepanel);
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