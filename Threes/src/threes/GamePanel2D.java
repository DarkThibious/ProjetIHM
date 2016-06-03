package threes;

import javax.swing.*;

public class GamePanel2D extends GamePanel
{	
	private Tuile2D[][] cases;
			
	public GamePanel2D(ThreesMain controller)
	{
		super(controller);
	}

	@Override
	public void setCase(int x, int y, int value) 
	{
		cases[x][y].update(value);
	}
}