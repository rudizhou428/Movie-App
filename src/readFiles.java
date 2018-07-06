import java.io.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class readFiles {


	public void readFile(String fName)
	{
		try {
			String temp = "";
			BufferedReader input = new BufferedReader(new FileReader(fName + ".txt"));

			while((temp = input.readLine()) != null)
			{
				String temparr[] = temp.split (" ");
				if(fName.equalsIgnoreCase("MovieData") == true)
				{
					variables.studentNumber.add(temparr[0]);
					variables.userWatched.add(temparr[1]);
					variables.score.add(Integer.parseInt(temparr[2]));
				}
				else if(fName.equalsIgnoreCase("passwords") == true)
				{
					variables.username.add(temparr[0]);
					variables.password.add(temparr[1]);
				}
				else if(fName.equalsIgnoreCase("movie") == true)
				{
					String holder[] = temp.split(" ", 2);
					variables.movieName.add(holder[0]);
					variables.genre.add(holder[1]);
				}
			}	
			input.close();

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "File Not Found!", "Movie", JOptionPane.ERROR_MESSAGE);
		}
	}
}
