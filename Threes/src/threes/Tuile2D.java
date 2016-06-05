package threes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Tuile2D extends JComponent{
	
	int value;
	JLabel text; 
	
	public Tuile2D()
	{
		super();
		setPreferredSize(new Dimension(100,100));
		setSize(getPreferredSize());
		//setBackground(Color.PINK);
		setFont(ThreesView.font);
		value = 0;
		text = new JLabel();
	    add(text);
	}
	
	public void paint(Graphics g) 
	{		
		 switch(this.value)
		 {
		 	case 1 : 
		 			g.setColor(new Color(57,160,182));
				 	g.fillRoundRect(0, 0, getSize().width, getSize().height,25,25);
			 		g.setColor(new Color(56,192,220));
				 	g.fillRoundRect(0, 0, getSize().width, (getSize().height)-5,25,25);
				 	this.setForeground(Color.WHITE); break;
				 	
		 	case 2 : 
			 		g.setColor(new Color(206,36,81));
				 	g.fillRoundRect(0, 0, getSize().width, getSize().height,25,25);
				 	g.setColor(new Color(206,80,113));
				 	g.fillRoundRect(0, 0, getSize().width, (getSize().height)-5,25,25);
				 	this.setForeground(Color.WHITE); break;
			 	
		 	case 0 :// this.setBackground(Color.gray); this.setForeground(Color.gray);break;
			 		g.setColor(new Color(158,178,200));
				 	g.fillRoundRect(0, 0, getSize().width, getSize().height,25,25);
				 	g.setColor(new Color(158,178,200));
				 	g.fillRoundRect(0, 0, getSize().width, (getSize().height)-5,25,25);
				 	this.setForeground(new Color(158,178,200)); break;
			 	
		 	default:
				 	g.setColor(new Color(224,166,82));
				 	g.fillRoundRect(0, 0, getSize().width, getSize().height,25,25);
				 	g.setColor(Color.white);
				 	g.fillRoundRect(0, 0, getSize().width, (getSize().height)-5,25,25);
				 	this.setForeground(Color.black); break;
				 	
		 }
	   //g.setColor(getBackground());
	   // g.fillRoundRect(0, 0, getSize().width, getSize().height,25,25);
	    g.setColor(getForeground());
	    g.drawString(Integer.toString(value), getSize().width/2 - g.getFontMetrics().stringWidth(Integer.toString(value))/2, getSize().height/2 + g.getFontMetrics().getHeight()/2);
	}
	public void update(int valeur)
	{
		 this.value = valeur;
		text.setText(Integer.toString(value));
		text.setForeground(getForeground());
		text.setLocation(this.getWidth()/2 - text.getWidth()/2, this.getHeight()/2 - text.getHeight()/2);
		text.setVisible(true);
		repaint();
		
	}
	  
	  public static void main(String args[]) throws InterruptedException
	  {
		  /*
		  JFrame test = new JFrame("Bonjour");
		  Tuile2D tuile = new Tuile2D();
		  tuile.setVisible(true);
		  test.getContentPane().add(tuile);
		  test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  test.setVisible(true);
		  test.setSize(100, 100);
		  Thread.sleep(2000);
		  tuile.update(1);
		  Thread.sleep(2000);
		  tuile.update(2);
		  Thread.sleep(2000);
		  tuile.update(3);
		  Thread.sleep(2000);
		  tuile.update(0);
		  */
	  }
}
