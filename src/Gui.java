import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

public class Gui extends JFrame{

	Container frame;
	login l = new login();
	
	ImageIcon img = new ImageIcon(getClass().getResource("/resources/icon.png"));


	public Gui(){

		super("Netflix");
		
		frame = getContentPane();

		frame.add(l);

		setIconImage(img.getImage());
		
		
		setSize(800,700);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args)
	{
		new Gui();
	}	

}
