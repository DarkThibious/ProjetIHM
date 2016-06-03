package threes;

import java.util.Random;

public class ThreesModel 
{
	private int [][] tuiles;
	private int score;
	private Random tirage;
	private boolean loss;
	private boolean full;
	
	public ThreesModel()
	{
		tuiles = new int[4][4];
		tirage = new Random();
		loss = false;
	}
	
	public void initPartie()
	{
		int x, y, i;
		for(x=0;x<4;x++)
		{
			for(y=0;y<4;y++)
			{
				tuiles[x][y] = 0;
			}
		}
		for(i=0;i<9;i++)
		{
			tuileAlea();
		}
		updateCases();
	}
	
	public boolean tuileAlea(int x, int y) 
	{
		if(tuiles[x][y] == 0)
		{
			tuiles[x][y] = tirage.nextInt(2)+1;
			return true;
		}
		return false;
	}
	
	public void tuileAleaX(int x)
	{
		boolean done = false;
		int y;
		do
		{
			y = tirage.nextInt(4);
			done = tuileAlea(x, y);
		}while(done == false);
	}
	
	public void tuileAleaY(int y)
	{
		boolean done = false;
		int x;
		do
		{
			x = tirage.nextInt(4);
			done = tuileAlea(x, y);
		}while(done == false);
	}
	
	public void tuileAlea()
	{
		boolean done = false;
		int x, y;
		do
		{
			x = tirage.nextInt(4);
			y = tirage.nextInt(4);
			done = tuileAlea(x, y);
		}while(done == false);
	}
	
	public boolean test(int x, int y, int x2, int y2)
	{
		if(tuiles[x2][y2] == 0)
		{
			return true;
		}
		else if (tuiles[x2][y2] == 2)
		{
			if(tuiles[x][y] == 1)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(tuiles[x2][y2] == 1)
		{
			if(tuiles[x][y] == 2)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else if(tuiles[x2][y2] == tuiles[x][y])
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void moveCase(int x, int y, int x2, int y2)
	{
		if(test(x, y, x2, y2))
		{
			tuiles[x2][y2] += tuiles[x][y];
			tuiles[x][y] = 0;
		}
	}
	
	public void moveUp()
	{
		int x, y;
		for(x=1;x<4;x++)
		{
			for(y=0;y<4;y++)
			{
				moveCase(x,y, x-1, y);
			}
		}
		if(full == false)
		{
			tuileAleaX(3);
		}
		updateCases();
	}
	
	public void moveDown()
	{
		int x, y;
		for(x=2;x>=0;x--)
		{
			for(y=0;y<4;y++)
			{
				moveCase(x,y, x+1, y);
			}
		}
		if(full == false)
		{
			tuileAleaX(0);
		}
		updateCases();
	}
	
	public void moveLeft()
	{
		int x, y;
		for(y=1;y<4;y++)
		{
			for(x=0;x<4;x++)
			{
				moveCase(x,y, x, y-1);
			}
		}
		if(full == false)
		{
			tuileAleaY(3);
		}
		updateCases();
	}
	
	public void moveRight()
	{
		int x, y;
		for(y=2;y>=0;y--)
		{
			for(x=0;x<4;x++)
			{
				moveCase(x,y, x, y+1);
			}
		}
		if(full == false)
		{
			tuileAleaY(0);
		}
		updateCases();
	}
	
	public void updateCases() 
	{
		calcScore();
		testFull();
		testPerdu();
	}
	
	private void calcScore()
	{
		int x,y;
		score = 0;
		for(x=0;x<4;x++)
		{
			for(y=0;y<4;y++)
			{
				score += tuiles[x][y];
			}
		}
	}
	
	private void testPerdu()
	{
		int x, y;
		loss = true;
		for(x=0;x<4;x++)
		{
			for(y=0;y<4;y++)
			{
				if(x != 0)
				{
					this.loss = !test(x, y, x-1, y);
				}
				if(x != 3 && this.loss)
				{
					this.loss = !test(x, y, x+1, y); 
				}
				if(y != 0 && this.loss)
				{
					this.loss = !test(x, y, x, y-1);
				}
				if(y != 3 && this.loss)
				{
					this.loss = !test(x, y, x, y+1);
				}
				if(!this.loss)
				{
					return;
				}
			}
		}
	}
	
	private void testFull()
	{
		full = true;
		int x, y;
		for(x=0;x<4;x++)
		{
			for(y=0;y<4;y++)
			{
				if(tuiles[x][y] == 0)
				{
					full = false;
					return;
				}
			}
		}
	}

	public boolean getLoss() 
	{
		return loss;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public int getValue(int x, int y)
	{
		return tuiles[x][y];
	}
}