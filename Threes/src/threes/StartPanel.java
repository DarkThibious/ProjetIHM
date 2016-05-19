package threes;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartPanel extends JPanel implements ThreesView
{
	private ThreesModel data;
	private ThreesMain control;

	private JButton start;
	private JLabel losslbl;
	
	public StartPanel(ThreesMain controller)
	{
		super();
		control = controller;
		data = control.getModel();
		start = new JButton("Commencer");
		losslbl = new JLabel("Perdu !");
		losslbl.setVisible(false);
		losslbl.setFont(font);
		losslbl.setHorizontalAlignment(JLabel.CENTER);
		
		setLayout(new BorderLayout(5,5));
		add(start, BorderLayout.CENTER);
		add(losslbl, BorderLayout.NORTH);
		
		start.addActionListener(buttonStart);
		
		this.setVisible(true);
	}
	
	public void update()
	{
		if(data.getLoss())
		{
			losslbl.setVisible(true);
			start.setText("Recommencer");
		}
		else
		{
			losslbl.setVisible(false);
			this.setVisible(false);
		}
	}
	
	public ActionListener buttonStart = new ActionListener() 
	{	
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			control.initPartie();
		}
	};
	
}