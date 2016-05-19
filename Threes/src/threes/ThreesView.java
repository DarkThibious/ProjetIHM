package threes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ThreesView extends JFrame
{
	private JLabel[][] cases;
	private JButton start;
	private JPanel startPanel;
	private JPanel gamePanel;
	private JLabel losslbl;
	private JMenu menu;
	
	public ThreesView(String title)
	{
		super(title);
		setLocationRelativeTo(null);
		setLocation(getX()-getWidth()/2, getY()-getHeight()/2);
		init();
	}
	
	public void init()
	{
		Font font = new Font("Arial", 0, 22);
		
		menu = new JMenu();
		menu.insert("Haut", 0);
		menu.insert("Bas", 1);
		menu.insert("Gauche", 2);
		menu.insert("Droite", 3);
		menu.setVisible(true);
		menu.setPopupMenuVisible(false);
		getContentPane().add(menu);
		
		startPanel = new JPanel();
		start = new JButton("Commencer");
		losslbl = new JLabel("Perdu !");
		losslbl.setVisible(false);
		losslbl.setFont(font);
		losslbl.setHorizontalAlignment(JLabel.CENTER);
		
		startPanel.setLayout(new BorderLayout(5,5));
		startPanel.add(start, BorderLayout.CENTER);
		startPanel.add(losslbl, BorderLayout.NORTH);
		
		gamePanel = new JPanel();
		cases = new JLabel[4][4];

		Border line = BorderFactory.createLineBorder(Color.black);
		
		gamePanel.setLayout(new GridLayout(4,4));
		gamePanel.setFocusable(true);
		
		int x, y;
		for(x =0;x<4;x++)
		{
			for(y=0;y<4;y++)
			{
				JPanel tmp = new JPanel();
				cases[x][y] = new JLabel("");
				cases[x][y].setFont(font);
				cases[x][y].setHorizontalAlignment(JLabel.CENTER);
				cases[x][y].setVerticalAlignment(JLabel.CENTER);
				tmp.setBorder(line);
				tmp.add(cases[x][y]);
				gamePanel.add(tmp);
			}
		}
		
		getContentPane().add(startPanel);
		setVisible(true);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void initPartie()
	{
		getContentPane().remove(startPanel);
		getContentPane().add(gamePanel);
		gamePanel.requestFocus();
		System.out.println("Start");
		pack();
	}
	
	public void afficherMenu(int x, int y)
	{
		menu.setMenuLocation(x, y);
		menu.setPopupMenuVisible(true);
		menu.requestFocus();
	}
	
	public void enleverMenu()
	{
		menu.setPopupMenuVisible(false);
	}
	
	public void perdu()
	{
		enleverMenu();
		losslbl.setVisible(true);
		start.setText("Recommencer");
		getContentPane().remove(gamePanel);
		getContentPane().add(startPanel);
		pack();
	}
	
	public void setCase(int x, int y, int value)
	{
		if(value != 0)
		{
			cases[x][y].setText(Integer.toString(value));
		}
		else
		{
			cases[x][y].setText("");
		}
		
	}
	
	public JButton getStartButton()
	{
		return this.start;
	}
	
	public JPanel getStartPanel()
	{
		return this.startPanel;
	}
	
	public JPanel getGamePanel()
	{
		return this.gamePanel;
	}
	
	public JMenu getMenu()
	{
		return this.menu;
	}
}