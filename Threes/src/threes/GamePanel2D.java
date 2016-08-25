package threes;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class GamePanel2D extends GamePanel
{	
	private Tuile2D[][] cases;
	
	public GamePanel2D(ThreesMain controller)
	{
		super(controller);
		cases = new Tuile2D[4][4];
		Border line = BorderFactory.createLineBorder(Color.black);
		int x, y;
		for(x =0;x<4;x++)
		{
			for(y=0;y<4;y++)
			{
				cases[x][y] = new Tuile2D();
				cases[x][y].setBorder(line);
				TabPanel.add(cases[x][y]);
			}
		}
	}

	@Override
	public void setCase(int x, int y, int value) 
	{
		cases[x][y].update(value);
	}
}