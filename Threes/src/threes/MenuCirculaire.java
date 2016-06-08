package threes;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class MenuCirculaire extends JComponent {
	int x,y,largeur,hauteur,angleDebut,angleArc;

	public MenuCirculaire(int largeur,int hauteur) {
		super();
		this.largeur=largeur;
		this.hauteur=hauteur;
	}


	public boolean estDans(double valX, double valY)
	{
	    return ((x<=valX)&&(x+largeur>=valX)
	           &&(y<=valY))&&(y+hauteur>=valY);
	}

	public void paint(Graphics g) {
	    g.setColor(Color.red);
	    g.fillArc(0,0,this.largeur,this.hauteur,45,90);
	    g.setColor(Color.green);
	    g.fillArc(0,0,this.largeur,this.hauteur,135,90);
	    g.setColor(Color.blue);
	    g.fillArc(0,0,this.largeur,this.hauteur,225,90);
	    g.setColor(Color.white);
	    g.fillArc(0,0,this.largeur,this.hauteur,315,90);
	  }



	public void setPosition(int posX, int posY) {
		 this.x = posX;
		 this.y = posY;

	}
}
