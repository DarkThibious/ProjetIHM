package threes;

import java.awt.Color;
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
		setBackground(Color.PINK);
		setFont(ThreesView.font);
		value = 0;
		text = new JLabel();
	}
	
	public void paint(Graphics g) 
	{		
	    g.setColor(getBackground());
	    g.fillRect(0, 0, 100, 100);
	    g.setColor(getForeground());
	}
	 public void update(int valeur)
	 {
		 this.value = valeur;
		 switch(this.value)
		 {
		 	case 1 : this.setBackground(Color.blue); this.setForeground(Color.WHITE); break;
		 	case 2 : this.setBackground(Color.red); this.setForeground(Color.WHITE); break;
		 	case 0 : this.setBackground(Color.gray); break;
		 	default: this.setBackground(Color.WHITE); this.setForeground(Color.BLACK); break;
		 }
		 if(value != 0)
		 {
			 text.setText(Integer.toString(value));
			 text.setForeground(getForeground());
			 text.setLocation(this.getWidth()/2 - text.getWidth()/2, this.getHeight()/2 - text.getHeight()/2);
			 text.setVisible(true);
		 }
		 else
		 {
			 text.setVisible(false);
		 }
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
