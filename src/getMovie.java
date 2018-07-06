import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class getMovie {

	private ArrayList<String> movie = new ArrayList();

	public getMovie()
	{
		readfile("movieNames");
		writefile("movieNames");
	}


	private void readfile(String fname) {

		try {
			String temp = "";
			String previous = "" ;


			BufferedReader input = new BufferedReader(new FileReader(fname + ".txt"));

			while((temp = input.readLine()) != null)
			{
				movie.add(temp);
			}	
			input.close();

			
			Object[] st = movie.toArray();
			for (Object s : st)
			{
				if (movie.indexOf(s) != movie.lastIndexOf(s)) 
				{
					movie.remove(movie.lastIndexOf(s));
				}
			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "File Not Found!", "Movie", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void writefile(String fname) {

		try
		{
			PrintWriter output = new PrintWriter (new FileWriter (fname + ".txt"), true);

			for(int i = 0; i < movie.size()- 1; i++)
			{	
				if(i == movie.size()- 2)
				{
					output.print(movie.get(i));
				}
				else
				{
					output.println(movie.get(i));
				}
			}


			output.close ();
		}
		catch (IOException e)
		{ // Error if array is empty
			JOptionPane.showMessageDialog (null, "No Info Found", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

}
