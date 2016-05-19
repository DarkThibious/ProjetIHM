package threes;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class ThreesControl 
{
	private ThreesModel data;
	
	Point mouse1;
	
	public ThreesControl()
	{
		
	}

	public static void main(String args[])
	{
		ThreesControl Three = new ThreesControl();
	}
}
