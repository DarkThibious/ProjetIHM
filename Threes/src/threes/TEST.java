package threes;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TEST extends JFrame
{
	private PopupCirculaire pop;
	
	public TEST()
	{
		super();
		setVisible(true);
		getContentPane().setLayout(new FlowLayout());
		JButton b = new JButton("FUCK");
		b.setVisible(true);
		getContentPane().add(b);
		pop = new PopupCirculaire(100, 100, -45);
		getContentPane().add(pop);
		pop.setVisible(true);
		pop.setOpaque(true);
		pop.addItem("HAUT");
		pop.addItem("BAS");
		pop.addItem("GAUCHE");
		pop.addItem("DROITE");
		pop.repaint();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
	
	public static void main(String[] args)
	{
		new TEST();
	}
}
