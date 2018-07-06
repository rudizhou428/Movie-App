import java.util.*;
public class sortFile {

	TreeMap<String, String> password = new TreeMap<String, String>();
	TreeMap<String, String> movie = new TreeMap<String, String>();

	public void sort (String file)
	{
		if(file.equalsIgnoreCase("passwords") == true)
		{
			for(int i = 0; i < variables.username.size();i++)
			{
				password.put(variables.username.get(i), variables.password.get(i));
			}
		}
		else if(file.equalsIgnoreCase("movie") == true)
		{
			for(int i = 0; i < variables.movieName.size();i++)
			{
				movie.put(variables.movieName.get(i), variables.genre.get(i));
			}

		}

	}
}
