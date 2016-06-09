package threes;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class ItemCirc extends JComponent 
{
	int angleDebut,angleArc, l, h;
	JLabel txt;
	Color couleur;
	
	public ItemCirc(String texte, Color couleur)
	{
		this.txt = new JLabel(texte);
		this.angleDebut = 0;
		this.angleArc = 0;
		this.couleur = couleur;
		this.setBackground(this.couleur);
		txt.setFont(ThreesView.font);
		txt.setOpaque(false);
		txt.setLocation(getWidth()/2-txt.getWidth()/2, getHeight()/2-txt.getHeight()/2);
		txt.setForeground(Color.BLACK);
		this.add(txt);
		
	}
	
	@Override
	public boolean contains(int x, int y)
	{
		x -= getX();
		y -= getY();
		
		double a = Math.acos(x/Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2)))*180/Math.PI; 
		if(y>0)
		{
			a = -a; 
		}
		if(a>45)
		{
			a -= 360;
		}
		if(a <= angleDebut && a >= angleDebut+angleArc)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.setColor(getBackground());
		g.fillArc(0, 0, l, h, angleDebut, angleArc);
		txt.paint(g);
	}
}
