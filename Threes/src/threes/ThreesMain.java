package threes;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class ThreesMain extends JFrame
{
	private JPanel contentPane;
    private StartPanel startpanel;
    private GamePanel gamepanel;
	private ArrayList<ThreesView> views = new ArrayList<ThreesView>();
    private ThreesModel model;
   File fichier;

    private int[] scores; // tableau des meilleurs scores
    public void gestionScores()
    {
    	int i=0;
    	boolean existe;
    	
    	scores = new int[5];
		for (i=0; i<5; i++) scores[i]=0; // le tableau est initialisé à 0 
		
		fichier = new File("Scores.txt");
		if(fichier.exists()) existe = true; // on teste : si le fichier existe charger son contenu dans le tableau scores 
		else existe = false;                //sinon le le crée 
		try 
		{
			fichier.createNewFile();
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		
		if(existe == true) 
		{
			//si le fichier existe charger son contenu dans le tableau scores 
			lireFichier(fichier);
		}	
    }
    /* Retourne l'index où on doit inserer le score de la partie terminée*/
    public int trouvePosition(int scorePartieTerminee)
    {
    	int index=0;
    	if(scores[4] > scorePartieTerminee)
    	{
    		index=-1;
    		return index;
    	}
    	else
    	{
        	while((scores[index] > scorePartieTerminee) && (index<5))
        	{
        		index++;
        	}
        	return index;
    	}

    }
    
    /* insere dans le tableau des meilleurs scores le score de la partie , à la bonne place tout en déclant ce qu'il faut*/
    public void insereScore(int index, int scorePartieTerminee)
    {
    	int i=4;
    	while(i>index)
    	{
    		scores[i]=scores[i-1];
    		i-=1;
    	}
    	scores[i]=scorePartieTerminee;
    }
    /* lire fichier et remplir le tableau des scores*/
    public void lireFichier(File fichier)
    {
    	int i,line;
    	FileReader fr = null;
		try 
		{
			fr = new FileReader(fichier);
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		
		try
		{
			String ligne = br.readLine();
			i=0;
			while ((ligne != null) && (i<5))
			{
				line = Integer.parseInt(ligne);
				scores[i]=line;
				ligne = br.readLine();
				i+=1;
			}
			br.close();
			fr.close();
		}		
		catch (IOException e)
		{
			System.out.println("Erreur lors de la lecture:"+e.getMessage());
		}
    }
    /* Remplir le fichier à partir du tableau scores*/
    void ecrireFichier(File fichier)
    {
    	int index;
    	try
		{
			FileWriter fw = new FileWriter(fichier);
			for (index=0;index<5;index++)
			{
				fw.write(Integer.toString(scores[index]));
				fw.flush();
			    fw.write ("\r\n");
			}
			fw.close();
		}
		catch (IOException e)
		{
			System.out.println("Erreur lors de la lecture:"+e.getMessage());
		}
		
    	
    }
    /* Retourne une chaine de caractères qui comprend les scores , on en aura pour l'affichage des meilleurs scores dans l'interface*/
    public String afficherMeilleursScores()
    {
    	int i;
    	String chaine = "";
    	for(i=0;i<5;i++)
    	{
    		chaine+= "position"+i+" : "+String.valueOf(scores[i])+"\n";
    	}
    	return chaine;
    }
    public void init()
    {
    	/*****************J'ai appelé ici la gestion du score pour l'instant *************/
    	gestionScores();
    	contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

        startpanel = new StartPanel(this);
        contentPane.add(startpanel);
        this.addView(startpanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setLocation(getX() - getWidth()/2 , getY() - getHeight()/2);
     // Create the menus
     		final JMenuBar menuBar = new JMenuBar();
     		final  JMenu menu1 = new JMenu("Fichier");
     		final  JMenu menu2 = new JMenu("Aide");

     		final JMenuItem item1 = new JMenuItem("Meilleurs scores");
     		final JMenuItem item2 = new JMenuItem("Quitter");
     		final JMenuItem item3 = new JMenuItem("Consignes");
     	
     		menu1.add(item1);
     		menu1.add(item2);
     		menu2.add(item3);
     		menuBar.add(menu1);
     		menuBar.add(menu2);
     		setJMenuBar(menuBar);
     		
     		ActionListener menuListener = new ActionListener()
     		{
     			@Override
     		    public void actionPerformed(ActionEvent event)
     			{
     				JMenuItem menuListener = (JMenuItem) event.getSource();
     			
     				if(menuListener==item1)
     				{
     					// ouvrir une pop up avec les meilleurs scores
     					JOptionPane.showMessageDialog(contentPane,"Meilleurs scores : \n"+ afficherMeilleursScores());
     				}
     				else if(menuListener==item2)
     				{
     					// fermer la fenetre    
     					dispose();   
     					
     				}
     				else if(menuListener==item3)
     				{
     					// ouvrir une pop up avec l'aide
     					 JOptionPane.showMessageDialog(contentPane, "Déplacez les tuiles (clavier,souris,menu) afin de faire rencontrer les multiples de 3 ou les '2' et les '1' ");
     				}
     			}
     		    
     		   };
     			item1.addActionListener(menuListener);
     			item2.addActionListener(menuListener);
     			item3.addActionListener(menuListener);
        
        this.setVisible(true);
        pack();

    }

    public ThreesMain()
    {
        super("Threes");
        model = new ThreesModel();
        this.init();
    }

    public void addView(ThreesView view)
    {
        views.add(view);
    }

    public void removeView(ThreesView view)
    {
        views.remove(view);
    }

    public void updateAllViews()
    {
        for(ThreesView view : this.views)
        {
            view.update();
        }
    }
    
    public void initPartie()
    {	
    	if(startpanel.getInterfaceAv() == false)
    	{
    		gamepanel = new GamePanel1D(this);
    	}
    	else
    	{
    		gamepanel = new GamePanel2D(this);
    	}
        this.addView(gamepanel);
    	model.initPartie();
    	contentPane.remove(startpanel);
    	contentPane.add(gamepanel);
    	gamepanel.initPartie();
    	gamepanel.update();
    	setSize(new Dimension(400,400));
    }

    public static void main(String [] args)
    {
        new ThreesMain();
    }
    
    public void moveUp()
	{
    	model.moveUp();
    	gamepanel.update();
	}
	
	public void moveDown()
	{
		model.moveDown();
		gamepanel.update();
	}
	
	public void moveLeft()
	{
		model.moveLeft();
		gamepanel.update();
	}
	
	public void moveRight()
	{
		model.moveRight();
    	gamepanel.update();
   	}
	
	public void perdu()
	{ int index;
		gamepanel.enleverMenu();
		startpanel.update();
    	contentPane.remove(gamepanel);
		contentPane.add(startpanel);
    	pack();
    	index = trouvePosition(model.score);
    	if((index < 5)&&(index > -1))
    	{
    		insereScore(index,model.score);
    		JOptionPane.showMessageDialog(contentPane,"Bravo votre score de"+model.score+"" +
    		" a été ajouté au tableau des \n"+ "meilleurs scores pour le visualiser cliquer sur" +
    				" 'Meilleurs scores'\n"+"dans la barre des menus");
    		ecrireFichier(fichier);
    	}
	}
	
	public ThreesModel getModel()
	{
		return this.model;
	}

	public int[] getScores() {
		return scores;
	}

	public void setScores(int[] scores) {
		this.scores = scores;
	}

}