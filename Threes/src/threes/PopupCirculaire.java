package threes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.PopupMenu;
import java.security.Policy;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;

public class PopupCirculaire extends JComponent
{
	int angleDebut;
	ArrayList<ItemCirc> items;

	public PopupCirculaire(int largeur,int hauteur, int angleDebut) 
	{
		super();
		this.angleDebut = angleDebut;
		items = new ArrayList<ItemCirc>();
		setSize(new Dimension(largeur, hauteur));
	}
	
	public void addItem(String texte)
	{
		items.add(new ItemCirc(texte));
	}
	
	@Override
	public void paint(Graphics g)
	{
		int i;
		int angleArc = 360/(this.getComponentCount()+1);
		for(i=0; i<this.items.size();i++)
		{
			System.out.println("MERDE");
			this.items.get(i).angleDebut = angleDebut+angleArc*i;
			this.items.get(i).setBackground(Color.RED);
			this.items.get(i).angleArc = angleArc;
			this.items.get(i).l = this.getWidth();
			this.items.get(i).h = this.getHeight();
			this.items.get(i).paint(g);
		}
	}
}
