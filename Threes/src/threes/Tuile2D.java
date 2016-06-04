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
		setBackground(Color.PINK);
		setFont(ThreesView.font);
		value = 0;
		text = new JLabel();
	    add(text);
	}
	
	public void paint(Graphics g) 
	{		
	    g.setColor(getBackground());
	    g.fillRect(0, 0, getSize().width, getSize().height);
	    g.setColor(getForeground());
	    g.drawString(Integer.toString(value), getSize().width/2 - g.getFontMetrics().stringWidth(Integer.toString(value))/2, getSize().height/2 + g.getFontMetrics().getHeight()/2);
	}
	public void update(int valeur)
	{
		 this.value = valeur;
		 switch(this.value)
		 {
		 	case 1 : this.setBackground(Color.blue); this.setForeground(Color.WHITE); break;
		 	case 2 : this.setBackground(Color.red); this.setForeground(Color.WHITE); break;
		 	case 0 : this.setBackground(Color.gray); this.setForeground(Color.gray);break;
		 	default: this.setBackground(Color.WHITE); this.setForeground(Color.BLACK); break;
		 }
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
