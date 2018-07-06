import java.io.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class writeFile {

	private String username = "" , movie = "";
	private int score = 0;

	sortFile sF = new sortFile();

	public void writeInfo (String fName, boolean append)
	{
		try
		{
			FileWriter file =  new FileWriter (fName + ".txt", append);
			PrintWriter output = new PrintWriter (file);

			if(fName.equalsIgnoreCase("movie") == true)
			{
				sF.sort("movie");
				for(int i = 0; i < sF.movie.size(); i++)
				{	
					if(i == sF.movie.size()-1)
					{
						output.print(sF.movie.keySet().toArray()[i] + " " + sF.movie.values().toArray()[i]);
					}
					else
					{
						output.println(sF.movie.keySet().toArray()[i] + " " + sF.movie.values().toArray()[i]);
					}
				}
			}
			else if(fName.equalsIgnoreCase("passwords") == true)
			{
				sF.sort("passwords");
				for(int i = 0; i < sF.password.size(); i++)
				{	
					if(i == sF.password.size()-1)
					{
						output.print(sF.password.keySet().toArray()[i] + " " + sF.password.values().toArray()[i]);
					}
					else
					{
						output.println(sF.password.keySet().toArray()[i] + " " + sF.password.values().toArray()[i]);
					}
				}
			}
			else if(fName.equalsIgnoreCase("movieData") == true)
			{
				output.println();
				output.print(username + " " + movie + " " + score);
			}
			output.close ();
		}
		catch (IOException e)
		{ // Error if array is empty
			JOptionPane.showMessageDialog (null, "No Info Found", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
