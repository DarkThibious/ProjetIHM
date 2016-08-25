package threes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public abstract class GamePanel extends JPanel implements ThreesView
{
	private ThreesModel data;
	private ThreesMain control;

	protected JPanel TabPanel;

	private Point mouse1;

	private JLabel score;

	private JMenu menu;
	
	private PopupCirculaire pop;
	
	private Timer timer;

	public GamePanel(ThreesMain controller)
	{
		super();
		setFocusable(true);
		setVisible(true);

		control = controller;
		data = controller.getModel();
		score = new JLabel();
		timer = new Timer(1000, stayListener);

		setLayout(new BorderLayout(5, 5));

		TabPanel = new JPanel();

		pop = new PopupCirculaire(150, 150, 45);
		add(pop);
		pop.addItem("DROITE");
		pop.addItem("BAS");
		pop.addItem("GAUCHE");
		pop.addItem("HAUT");
	
		pop.setVisible(false);

		TabPanel.setLayout(new GridLayout(4,4));
		TabPanel.setVisible(true);

		add(TabPanel, BorderLayout.CENTER);
		add(score, BorderLayout.SOUTH);

		addKeyListener(keyMove);
		addMouseListener(mouseClick);
		
		((GridLayout) TabPanel.getLayout()).setHgap(5);
		((GridLayout) TabPanel.getLayout()).setVgap(5);
		TabPanel.setBackground(new Color(184,216,216));
		TabPanel.setOpaque(true);
	}

	public void initPartie()
	{
		requestFocus();
	}

	public void afficherMenu(int x, int y)
	{
		pop.setLocation(x-pop.getWidth()/2, y-pop.getHeight()/2);
		pop.setVisible(true);
		pop.repaint();
	}

	public void update()
	{
		int x, y;
		for(x =0;x<4;x++)
		{
			for(y=0;y<4;y++)
			{
				setCase(x, y, data.getValue(x, y));
			}
		}
		score.setText("Score : " + Integer.toString(data.getScore()));
		requestFocus();
	}

	public abstract void setCase(int x, int y, int value);

	public void testPop(int x, int y)
	{
		int i;
		for(i=0;i<pop.items.size();i++)
		{
			if(pop.items.get(i).contains(x-pop.getX()-pop.getWidth()/2, y-pop.getY()-pop.getHeight()/2))
			{
				switch(i)
				{
				case 0 :
					control.moveRight();
					break;
				case 1 :
					control.moveDown();
					break;
				case 2 :
					control.moveLeft();
					break;
				case 3 :
					control.moveUp();
					break;
				}
				System.out.println(i);
				break;
			}

		}
	}
	
	public JMenu getMenu()
	{
		return this.menu;
	}
	
	public ActionListener stayListener = new ActionListener() 
	{	
		public void actionPerformed(ActionEvent e) 
		{
			afficherMenu(mouse1.x, mouse1.y);
			
		}
	};

	public MouseListener mouseClick = new MouseListener()
	{
		@Override
		public void mouseReleased(MouseEvent e)
		{
			timer.stop();
			if(e.getButton() == MouseEvent.BUTTON1)
			{
				Point mouse2 = e.getPoint();
				int dx = mouse1.x - mouse2.x;
				int dy = mouse1.y - mouse2.y;

				if(Math.abs(dx) > 10 || Math.abs(dy) > 10)
				{
					if(Math.abs(dy) > Math.abs(dx))
					{
						if(dy > 0)
						{
							control.moveUp();
						}
						else
						{
							control.moveDown();
						}
					}
					else
					{
						if(dx > 0)
						{
							control.moveLeft();
						}
						else
						{
							control.moveRight();
						}
					}
					if(pop.isVisible())
					{
						pop.setVisible(false);
					}
				}
				else if(pop.isVisible())
				{
					testPop(e.getX(), e.getY());
					pop.setVisible(false);
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e)
		{
			if(data.getLoss())
			{
				control.perdu();
			}

			else if(e.getButton() == MouseEvent.BUTTON1)
			{
				mouse1 = e.getPoint();
				timer.restart();
			}
		}

		@Override
		public void mouseExited(MouseEvent e){}

		@Override
		public void mouseEntered(MouseEvent e){}

		@Override
		public void mouseClicked(MouseEvent e)
		{
			if(pop.isVisible())
			{
				if(pop.contains(e.getX(), e.getY()))
				{
					testPop(e.getX(), e.getY());
				}
				pop.setVisible(false);
			}
			if(e.getButton() == MouseEvent.BUTTON3)
			{
				afficherMenu(e.getX(), e.getY());
			}
		}
	};

	public KeyListener keyMove = new KeyListener()
	{
		@Override
		public void keyTyped(KeyEvent e){}
		@Override
		public void keyReleased(KeyEvent e){}

		@Override
		public void keyPressed(KeyEvent e)
		{
			if(data.getLoss())
			{
				control.perdu();
			}
			else if(e.getKeyCode() == KeyEvent.VK_UP)
			{
				control.moveUp();
			}
			else if(e.getKeyCode() == KeyEvent.VK_DOWN)
			{
				control.moveDown();
			}
			else if(e.getKeyCode() == KeyEvent.VK_LEFT)
			{
				control.moveLeft();
			}
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				control.moveRight();
			}
		}
	};

}