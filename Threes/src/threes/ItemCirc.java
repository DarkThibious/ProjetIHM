package threes;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class ItemCirc extends JComponent 
{
	int angleDebut,angleArc, l, h;
	String txt;
	Color couleur;
	
	public ItemCirc(String texte, Color couleur)
	{
		this.txt = texte;
		this.angleDebut = 0;
		this.angleArc = 0;
		this.couleur = couleur;
		this.setBackground(this.couleur);
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.setColor(getBackground());
		g.fillArc(0, 0, l, h, angleDebut, angleArc);
		g.setColor(Color.black);
	}
	
	

}
