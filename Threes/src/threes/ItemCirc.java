package threes;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class ItemCirc extends JComponent 
{
	int angleDebut,angleArc, l, h;
	String txt;
	
	public ItemCirc(String texte)
	{
		this.txt = texte;
		this.angleDebut = 0;
		this.angleArc = 0;
	}
	
	@Override
	public void paint(Graphics g)
	{
		System.out.println("MERDE");
		g.setColor(getBackground());
		g.fillArc(0, 0, l, h, angleDebut, angleArc);
		g.setColor(Color.black);
		g.drawArc(0, 0, l, h, angleDebut, angleArc);
		g.drawLine(0, 0, (int) (l*Math.cos(angleDebut)), (int) (h*Math.sin(angleDebut)));
	}
	
	

}
