package threes;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class ThreesControl 
{
	private ThreesModel data;
	private ThreesView view;
	
	Point mouse1;
	
	public ThreesControl()
	{
		view = new ThreesView("Threes");
		data = new ThreesModel(view);
		view.getStartButton().addActionListener(buttonStart);
		view.getGamePanel().addKeyListener(keyMove);
		view.getGamePanel().addMouseListener(mouseClick);
		view.getMenu().getItem(0).addActionListener(menuHaut);
		view.getMenu().getItem(1).addActionListener(menuBas);
		view.getMenu().getItem(2).addActionListener(menuGauche);
		view.getMenu().getItem(3).addActionListener(menuDroite);
	}
	
	public ActionListener menuHaut = new ActionListener() 
	{
		
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			if(data.getLoss())
			{
				view.perdu();
			}
			else
			{
				data.moveUp();
			}
		}
	};
	
	public ActionListener menuBas = new ActionListener() 
	{
		
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			
			if(data.getLoss())
			{
				view.perdu();
			}
			else
			{
				data.moveDown();
			}
		}
	};
	
	public ActionListener menuDroite = new ActionListener() 
	{
		
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			if(data.getLoss())
			{
				view.perdu();
			}
			else
			{
				data.moveRight();
			}
		}
	};
	
	public ActionListener menuGauche = new ActionListener() 
	{
		
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			if(data.getLoss())
			{
				view.perdu();
			}
			else
			{
				data.moveLeft();
			}
		}
	};
	
	public ActionListener buttonStart = new ActionListener() 
	{	
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			data.initPartie();
			view.initPartie();
		}
	};
	
	public MouseListener mouseClick = new MouseListener() 
	{
		@Override
		public void mouseReleased(MouseEvent e)
		{
			if(e.getButton() == MouseEvent.BUTTON1)
			{
				Point mouse2 = e.getPoint();
				int dx = mouse1.x - mouse2.x;
				int dy = mouse1.y - mouse2.y;
				
				if(Math.abs(dx) < 10 && Math.abs(dy) < 10) // Pour éviter de prendre en compte les mouvements trop petits
				{
					return;
				}
				if(Math.abs(dy) > Math.abs(dx))
				{
					if(dy > 0)
					{
						data.moveUp();
					}
					else
					{
						data.moveDown();
					}
				}
				else
				{
					if(dx > 0)
					{
						data.moveLeft();
					}
					else
					{
						data.moveRight();
					}
				}
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e)
		{
			if(data.getLoss())
			{
				view.perdu();
			}
			else if(e.getButton() == MouseEvent.BUTTON1)
			{
				mouse1 = e.getPoint();
			}
		}
		
		@Override
		public void mouseExited(MouseEvent e){}
		
		@Override
		public void mouseEntered(MouseEvent e){}
		
		@Override
		public void mouseClicked(MouseEvent e)
		{
			if(!view.getMenu().contains(e.getPoint()) && view.getMenu().isPopupMenuVisible())
			{
				data.view.enleverMenu();
			}
			if(e.getButton() == MouseEvent.BUTTON3)
			{
				System.out.println("Blug");
				data.view.afficherMenu(e.getX(), e.getY());
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
				view.perdu();
			}
			else if(e.getKeyCode() == KeyEvent.VK_UP)
			{
				data.moveUp();
			}
			else if(e.getKeyCode() == KeyEvent.VK_DOWN)
			{
				data.moveDown();
			}
			else if(e.getKeyCode() == KeyEvent.VK_LEFT)
			{
				data.moveLeft();
			}
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				data.moveRight();
			}
		}
	};

	public static void main(String args[])
	{
		ThreesControl Three = new ThreesControl();
	}
}
