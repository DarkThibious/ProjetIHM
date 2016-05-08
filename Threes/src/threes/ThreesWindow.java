package threes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.dnd.MouseDragGestureRecognizer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ThreesWindow extends JFrame
{
	private JLabel[][] tuiles;
	private JButton start;
	private Random tirage;
	private Point mouse1;
	private JPanel startPanel;
	private JPanel gamePanel;
	private JLabel losslbl;
	
	public ThreesWindow(String title)
	{
		super(title);
		setLocationRelativeTo(null);
		setLocation(getX()-getWidth()/2, getY()-getHeight()/2);
		tirage = new Random();
		init();
	}
	
	public ActionListener buttonStart = new ActionListener() 
	{	
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			initPartie();
		}
	};
	
	public MouseListener mouseClick = new MouseListener() 
	{
		
		@Override
		public void mouseReleased(MouseEvent e)
		{
			Point mouse2 = e.getPoint();
			int dx = mouse1.x - mouse2.x;
			int dy = mouse1.y - mouse2.y;
			
			System.out.println("dx : " + dx + " dy : " + dy);
			
			if(Math.abs(dx) < 10 && Math.abs(dy) < 10) // Pour éviter de prendre en compte les mouvements trop petits
			{
				return;
			}
			if(Math.abs(dy) > Math.abs(dx))
			{
				if(dy > 0)
				{
					moveUp();
				}
				else
				{
					moveDown();
				}
			}
			else
			{
				if(dx > 0)
				{
					moveLeft();
				}
				else
				{
					moveRight();
				}
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e)
		{
			mouse1 = e.getPoint();
		}
		
		@Override
		public void mouseExited(MouseEvent e){}
		
		@Override
		public void mouseEntered(MouseEvent e){}
		
		@Override
		public void mouseClicked(MouseEvent e){}
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
			if(e.getKeyCode() == KeyEvent.VK_UP)
			{
				moveUp();
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN)
			{
				moveDown();
			}
			if(e.getKeyCode() == KeyEvent.VK_LEFT)
			{
				moveLeft();
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				moveRight();
			}
		}
	};  
	
	private void moveCase(int x, int y, int x2, int y2)
	{
		if(tuiles[x2][y2].getText().compareTo("") == 0)
		{
			tuiles[x2][y2].setText(tuiles[x][y].getText());
			tuiles[x][y].setText("");
		}
		else if(tuiles[x2][y2].getText().compareTo("2") == 0 || tuiles[x2][y2].getText().compareTo("1") == 0)
		{
			if ((tuiles[x][y].getText().compareTo("2") == 0 || tuiles[x][y].getText().compareTo("1") == 0) && tuiles[x2][y2].getText().compareTo(tuiles[x][y].getText()) != 0)
			{
				tuiles[x2][y2].setText(Integer.toString(3));
				tuiles[x][y].setText("");
			}
		}
		else if (tuiles[x2][y2].getText().compareTo(tuiles[x][y].getText()) == 0)
		{
			tuiles[x2][y2].setText(Integer.toString((Integer.valueOf(tuiles[x][y].getText())*2)));
			tuiles[x][y].setText("");
		}
	}
	
	private void moveUp()
	{
		int x, y;
		for(x=1;x<4;x++)
		{
			for(y=0;y<4;y++)
			{
				moveCase(x,y, x-1, y);
			}
		}
		nbAleaX(3);
	}
	
	private void moveDown()
	{
		int x, y;
		for(x=2;x>=0;x--)
		{
			for(y=0;y<4;y++)
			{
				moveCase(x,y, x+1, y);
			}
		}
		nbAleaX(0);
	}
	
	private void moveLeft()
	{
		int x, y;
		for(y=1;y<4;y++)
		{
			for(x=0;x<4;x++)
			{
				moveCase(x,y, x, y-1);
			}
		}
		nbAleaY(3);
	}
	
	private void moveRight()
	{
		int x, y;
		for(y=2;y>=0;y--)
		{
			for(x=0;x<4;x++)
			{
				moveCase(x,y, x, y+1);
			}
		}
		nbAleaY(0);
	}
	
	public void nbAlea()
	{
		boolean done = false;
		int x, y;
		int i = 0;
		do
		{
			x = tirage.nextInt(4);
			y = tirage.nextInt(4);
			done = nbAlea(x, y);
			i++;
		}while(done == false && i<15000);
		if(i==15000)
		{
			perdu();
		}
	}
	
	public void nbAleaX(int x)
	{
		boolean done = false;
		int i = 0;
		int y;
		do
		{
			y = tirage.nextInt(4);
			done = nbAlea(x, y);
			i++;
		}while(done == false && i<15000);
		if(i==15000)
		{
			perdu();
		}
	}
	
	public void nbAleaY(int y)
	{
		boolean done = false;
		int i=0;
		int x;
		do
		{
			x = tirage.nextInt(4);
			done = nbAlea(x, y);
			i++;
		}while(done == false && i<15000);
		if(i==15000)
		{
			perdu();
		}
	}
	
	public boolean nbAlea(int x, int y)
	{
		if(tuiles[x][y].getText().compareTo("") == 0)
		{
			tuiles[x][y].setText(Integer.toString(tirage.nextInt(2)+1));
			return true;
		}
		return false;
	}

	public void perdu()
	{
		losslbl.setVisible(true);
		start.setText("Recommencer");
		getContentPane().remove(gamePanel);
		getContentPane().add(startPanel);
		pack();
	}
	
	public void init()
	{
		Font font = new Font("Arial", 0, 22);
		
		startPanel = new JPanel();
		start = new JButton("Commencer");
		losslbl = new JLabel("Perdu !");
		losslbl.setVisible(false);
		losslbl.setFont(font);
		losslbl.setHorizontalAlignment(JLabel.CENTER);
		start.addActionListener(buttonStart);
		startPanel.setLayout(new BorderLayout(5,5));
		startPanel.add(start, BorderLayout.CENTER);
		startPanel.add(losslbl, BorderLayout.NORTH);
		
		gamePanel = new JPanel();
		tuiles = new JLabel[4][4];

		Border line = BorderFactory.createLineBorder(Color.black);
		
		gamePanel.setLayout(new GridLayout(4,4));
		gamePanel.addKeyListener(keyMove);
		gamePanel.addMouseListener(mouseClick);
		gamePanel.setFocusable(true);
	//	((GridLayout) mainPanel.getLayout()).setHgap(10);
	//	((GridLayout) mainPanel.getLayout()).setVgap(10);
		
		int i, x, y;
		for(x =0;x<4;x++)
		{
			for(y=0;y<4;y++)
			{
				JPanel tmp = new JPanel();
				tuiles[x][y] = new JLabel("");
				tuiles[x][y].setFont(font);
				tuiles[x][y].setHorizontalAlignment(JLabel.CENTER);
				tuiles[x][y].setVerticalAlignment(JLabel.CENTER);
				tmp.setBorder(line);
				tmp.add(tuiles[x][y]);
				gamePanel.add(tmp);
			}
		}
		
		for(i=0;i<9;i++)
		{
			nbAlea();
		}
		
		getContentPane().add(startPanel);
		setVisible(true);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void initPartie()
	{
		int i, x, y;
		for(x =0;x<4;x++)
		{
			for(y=0;y<4;y++)
			{
				tuiles[x][y].setText("");
			}
		}
		
		for(i=0;i<9;i++)
		{
			nbAlea();
		}
		getContentPane().remove(startPanel);
		getContentPane().add(gamePanel);
		pack();
	}
	
	public static void main(String[] args)
	{
		ThreesWindow Main = new ThreesWindow("Threes"); 
	}
}
