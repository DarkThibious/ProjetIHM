package threes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class PopupCirculaire extends JComponent
{
	int angleDebut;
	ArrayList<ItemCirc> items;
	Color[] colors;
	

	public PopupCirculaire(int largeur,int hauteur, int angleDebut) 
	{
		super();
		this.angleDebut = angleDebut;
		items = new ArrayList<ItemCirc>();
		setSize(new Dimension(largeur, hauteur));
		colors = new Color[8]; 
		colors[0] = new Color(Color.YELLOW.getRed(),Color.YELLOW.getGreen(), Color.YELLOW.getBlue(), 200);
		colors[1] = new Color(Color.GREEN.getRed(),Color.GREEN.getGreen(), Color.GREEN.getBlue(), 200);
		colors[2] = new Color(Color.ORANGE.getRed(),Color.ORANGE.getGreen(), Color.ORANGE.getBlue(), 200);
		colors[3] = new Color(Color.PINK.getRed(),Color.PINK.getGreen(), Color.PINK.getBlue(), 200);
	}
	
	public void addItem(String texte)
	{
		items.add(new ItemCirc(texte, colors[this.items.size()%4]));
	}
	
	@Override
	public void paint(Graphics g)
	{
		int i;
		int angleArc = -360/(this.items.size());
		for(i=0; i<this.items.size();i++)
		{
			this.items.get(i).angleDebut = angleDebut+angleArc*i;
			this.items.get(i).angleArc = angleArc;
			this.items.get(i).l = this.getWidth()-5;
			this.items.get(i).h = this.getHeight()-5;
			this.items.get(i).paint(g);
		}
	}
	@Override
	public boolean contains(int x,int y)
	{
		x -= getX();
		y -= getY();
		if(Math.pow((x-getWidth()/2),2)+Math.pow((y-getHeight()/2),2) > Math.pow(getHeight()/2,2))
		{
			return false;
		}	
		else
		{	
			return true;
		}	
	}
}
