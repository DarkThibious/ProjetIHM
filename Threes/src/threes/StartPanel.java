package threes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class StartPanel extends JPanel implements ThreesView
{
	private ThreesModel data;
	private ThreesMain control;

	private JButton start;
	private JLabel losslbl;
	private JLabel scorelbl;
	JRadioButton option1 = new JRadioButton("Interface 1");
    JRadioButton option2 = new JRadioButton("Interface 2");
    ButtonGroup group = new ButtonGroup();
    Boolean Interface;
	
	public StartPanel(ThreesMain controller)
	{
		super();
		control = controller;
		data = control.getModel();
		start = new JButton("Commencer");
		start.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		losslbl = new JLabel("Perdu !");
		losslbl.setVisible(false);
		losslbl.setFont(font);
		losslbl.setHorizontalAlignment(JLabel.CENTER);
		losslbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		scorelbl = new JLabel();
		scorelbl.setVisible(false);
		scorelbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		start.setHorizontalAlignment(JButton.CENTER);
		losslbl.setHorizontalAlignment(JLabel.CENTER);
		scorelbl.setHorizontalAlignment(JLabel.CENTER);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setLayout(new FlowLayout());
		add(losslbl);
		add(scorelbl);
		add(start);
		
		start.addActionListener(buttonStart);
		
		start.requestFocus();
		
		this.setVisible(true);
		
	/* Radio Button */
       
        group.add(option1);
        group.add(option2);
        setLayout(new FlowLayout());
        add(option1);
        add(option2);
        
		option1.addActionListener(radioButton);
		option2.addActionListener(radioButton);

   	 setInterfaceAv(false);
   	option1.setSelected(true);
	}
	
	public void update()
	{
		if(data.getLoss())
		{
			losslbl.setVisible(true);
			scorelbl.setVisible(true);
			scorelbl.setText("Score final : "+ Integer.toString(data.getScore()));
			start.setText("Recommencer");
			start.requestFocus();
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
	
	
	public ActionListener radioButton = new ActionListener()
	{
		@Override
	    public void actionPerformed(ActionEvent event)
		{
		 JRadioButton radioButton = (JRadioButton) event.getSource();
	     if (radioButton == option1) 
	    	 setInterfaceAv(false);
	     else if (radioButton == option2)  
	    	 setInterfaceAv(true);	
	    } 
	};

	public Boolean getInterfaceAv() {
		return Interface;
	}

	public void setInterfaceAv(Boolean intefaceAv) {
		this.Interface = intefaceAv;
	}
	
	
	
	
}