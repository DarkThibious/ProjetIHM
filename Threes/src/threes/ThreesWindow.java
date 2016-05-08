package threes;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
	private Random tirage;
	
	public ThreesWindow(String title)
	{
		super(title);
		tirage = new Random();
		init();
	}
	
	public KeyListener move = new KeyListener() 
	{
		
		@Override
		public void keyTyped(KeyEvent e) 
		{
			return;
		}
		
		@Override
		public void keyReleased(KeyEvent e) 
		{
			return;
		}
		
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
		do
		{
			x = tirage.nextInt(4);
			y = tirage.nextInt(4);
			done = nbAlea(x, y);
		}while(done == false);
	}
	
	public void nbAleaX(int x)
	{
		boolean done = false;
		int y;
		do
		{
			y = tirage.nextInt(4);
			done = nbAlea(x, y);
		}while(done == false);
	}
	
	public void nbAleaY(int y)
	{
		boolean done = false;
		int x;
		do
		{
			x = tirage.nextInt(4);
			done = nbAlea(x, y);
		}while(done == false);
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
	
	public void init()
	{
		tuiles = new JLabel[4][4];
		
		Container mainPanel= getContentPane();
		
		Font font = new Font("Arial", 0, 22);
		Border line = BorderFactory.createLineBorder(Color.black);
		
		mainPanel.setLayout(new GridLayout(4,4));
		mainPanel.addKeyListener(move);
		mainPanel.setFocusable(true);
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
				tmp.setBorder(line);
				tmp.add(tuiles[x][y]);
				mainPanel.add(tmp);
			}
		}
		Boolean done;
		for(i=0;i<9;i++)
		{
			nbAlea();
		}
		setVisible(true);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args)
	{
		ThreesWindow Main = new ThreesWindow("Threes"); 
	}
}
