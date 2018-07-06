import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
public class starRating extends JPanel implements MouseListener{

	//JPanel stars = new JPanel();

	JLabel[] pictures = new JLabel[10];
	boolean[] allowForward = new boolean[10];
	boolean[] golden = new boolean[10];
	boolean[] allowBackward= new boolean[10];

	ImageIcon starRight = new ImageIcon(getClass().getResource("/resources/halfRight.png"));
	ImageIcon starRightC = new ImageIcon(getClass().getResource("/resources/halfRightC.png"));
	ImageIcon starLeft = new ImageIcon(getClass().getResource("/resources/halfLeft.png"));
	ImageIcon starLeftC = new ImageIcon(getClass().getResource("/resources/halfLeftC.png"));

	public starRating()
	{

		setLayout(new GridLayout(1,10));
		
		Arrays.fill(allowForward, false);
		Arrays.fill(allowBackward, false);
		Arrays.fill(golden, false);

		for(int i = 0; i < 10;i+=2)
		{
			pictures[i] = new JLabel(starLeft);
			pictures[i].addMouseListener(this);
		}
		for(int i = 1; i < 10;i+=2)
		{
			pictures[i] = new JLabel(starRight);
			pictures[i].addMouseListener(this);
		}

		for(int i = 0; i < 10; i++)
		{
			add(pictures[i]);
		}

		

	}

	public void reset()
	{
		for(int i = 0; i < 10;i+=2)
		{
			pictures[i].setIcon(starLeft);
		}
		for(int i = 1; i < 10;i+=2)
		{
			pictures[i].setIcon(starRight);
		}
		Arrays.fill(allowForward, false);
		Arrays.fill(allowBackward, false);
		Arrays.fill(golden, false);
	}
	
	public boolean checkForward(int i)
	{
		if(allowForward[i] == true)
		{
			return true;
		}

		return false;
	}
	public void mouseClicked(MouseEvent e) 
	{



	}

	public void mouseEntered(MouseEvent e) {

		if(e.getSource() == pictures[0] && checkForward(0) == false)
		{
			pictures[0].setIcon(starLeftC);
			allowForward[1] = true;
			golden[0] = true;

		}
		else if(e.getSource() == pictures[1] && checkForward(1) == true)
		{

			pictures[1].setIcon(starRightC);
			allowForward[2] = true;
			golden[1] = true;
		}
		else if(e.getSource() == pictures[2] && checkForward(2) ==  true)
		{
			pictures[2].setIcon(starLeftC);
			allowForward[3] = true;
			golden[2] = true;
		}		
		else if(e.getSource() == pictures[3] && checkForward(3) ==  true)
		{
			pictures[3].setIcon(starRightC);
			allowForward[4] = true;
			golden[3] = true;

		}	
		else if(e.getSource() == pictures[4] && checkForward(4) ==  true)
		{
			pictures[4].setIcon(starLeftC);
			allowForward[5] = true;
			golden[4] = true;
		}		
		else if(e.getSource() == pictures[5] && checkForward(5) ==  true)
		{
			pictures[5].setIcon(starRightC);
			allowForward[6] = true;
			golden[5] = true;
		}		

		else if(e.getSource() == pictures[6]&& checkForward(6) ==  true)
		{
			pictures[6].setIcon(starLeftC);
			allowForward[7] = true;
			golden[6] = true;
		}		
		else if(e.getSource() == pictures[7] && checkForward(7) ==  true)
		{
			pictures[7].setIcon(starRightC);
			allowForward[8] = true;
			golden[7] = true;
		}	
		else if(e.getSource() == pictures[8] && checkForward(8) ==  true)
		{
			pictures[8].setIcon(starLeftC);
			allowForward[9] = true;
			golden[8] = true;
		}	
		else if(e.getSource() == pictures[9]  && checkForward(8) ==  true)
		{
			pictures[9].setIcon(starRightC);
			golden[9] = true;
		}
	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {


	}

}
