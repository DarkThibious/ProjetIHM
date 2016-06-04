package threes;

import static threes.ThreesView.font;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GamePanel1D extends GamePanel
{
	private JLabel[][] cases;

	public GamePanel1D(ThreesMain controller)
	{
		super(controller);
		cases = new JLabel[4][4];
		Border line = BorderFactory.createLineBorder(Color.black);
		
		int x, y;
		for(x =0;x<4;x++)
		{
			for(y=0;y<4;y++)
			{
				cases[x][y] = new JLabel("");
				cases[x][y].setFont(font);
				cases[x][y].setBackground(Color.BLUE);
				cases[x][y].setHorizontalAlignment(JLabel.CENTER);
				cases[x][y].setVerticalAlignment(JLabel.CENTER);
				cases[x][y].setOpaque(true);
				cases[x][y].setBorder(line);	
				TabPanel.add(cases[x][y]);
			}
		}
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
		switch(value)
		{
			case 1 : cases[x][y].setBackground(Color.BLUE); cases[x][y].setForeground(Color.WHITE); break;
			case 2 : cases[x][y].setBackground(Color.RED); cases[x][y].setForeground(Color.WHITE); break;
			case 0 : cases[x][y].setBackground(Color.GRAY); cases[x][y].setForeground(Color.BLACK); break;
			default : cases[x][y].setBackground(Color.WHITE); cases[x][y].setForeground(Color.BLACK); break;
		}
	}
	
}
