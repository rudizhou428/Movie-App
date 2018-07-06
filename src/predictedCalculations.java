import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class predictedCalculations{

	private String loggedUser;
	private int counter= 0,total = 0;
	boolean valid = false;
	private int movieCount[] = new int[16];
	private double averages[] = new double[16];
	private ArrayList<Integer> score = new ArrayList<Integer>();
	private ArrayList<String> movieList = new ArrayList<String>();
	private ArrayList<Double> ranking = new ArrayList<Double>();
	private ArrayList<String> updatedGenre = new ArrayList<String>();

	private String[] genreList = {"Adventure", "Biography", "Drama", "Thriller", "Comedy",
			"Action", "Fantasy", "War", "Horror", "Family", "Sci-Fi", "Mystery", "Sport",
			"Romance", "Crime", "Animation"};

	readFiles rF = new readFiles();
	writeFile wF = new writeFile();
	public predictedCalculations(String user)
	{
		for(int i = 0; i < genreList.length;i++)
		{
			score.add(0);
		}
		rF.readFile("movieData");
		rF.readFile("movie");
		loggedUser = user;
		Arrays.fill(movieCount, 0);
		getUserPicks();
		
		if(total > 50)
		{
			valid = true;
		}

		if(valid)
		{
			compare();

			for(int i = 0; i < (updatedGenre.size()- counter);i++)
			{
				getMovie(updatedGenre.get(i));
			}

			Collections.sort(movieList);
			updatedGenre.clear();

			filterMovie(); 

			Collections.shuffle(updatedGenre);
			shuffle();
		}
		else if(valid == false)
		{
			JOptionPane.showMessageDialog (null, "Not enough Information for Recommendations", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	public ArrayList<String> getmovieList() {
		return movieList;
	}

	public void shuffle()
	{
		ArrayList<String> copy = new ArrayList<String>(updatedGenre);
		movieList = new ArrayList(copy.subList(0, 10));
	}

	public void getUserPicks()
	{
		calculateAverage();
		Arrays.fill(movieCount, 0);
		Collections.fill(score, 0);

		for(int i = 0; i < variables.userWatched.size(); i++)
		{
			if(loggedUser.equalsIgnoreCase(variables.studentNumber.get(i)) == true)
			{
				String genre = "";
				genre = variables.genre.get(variables.movieName.indexOf(variables.userWatched.get(i)));
				calculateScore(genre.split(" "),variables.score.get(i));
			}
		}
		for(int i = 0; i < 16; i++)
		{
			total += score.get(i);
		}

		for(int i = 0; i < 16; i++)
		{
			if(score.get(i) != 0 || movieCount[i] != 0)
			{
				averages[i] = ((double)(score.get(i)) / (double)movieCount[i]);
			}
			else
			{
				averages[i] = 0;
			}	
		}

	}

	public void filterMovie()
	{
		Object[] filter = movieList.toArray();
		for (Object s : filter)
		{
			if (movieList.indexOf(s) != movieList.lastIndexOf(s)) 
			{
				int x = movieList.lastIndexOf(s);
				updatedGenre.add(movieList.get(x));
			}
		}

		Object[] remove = updatedGenre.toArray();
		for (Object s : remove)
		{
			if (updatedGenre.indexOf(s) != updatedGenre.lastIndexOf(s)) 
			{
				updatedGenre.remove(updatedGenre.lastIndexOf(s));
			}
		}	

		for(int i = 0; i < variables.userWatched.size(); i++)
		{
			if(loggedUser.equalsIgnoreCase(variables.studentNumber.get(i)) == true)
			{				
				for(int l = 0; l < updatedGenre.size(); l++)
				{
					if(variables.userWatched.get(i).equalsIgnoreCase(updatedGenre.get(l)))
					{
						updatedGenre.remove(l);
					}
				}
			}
		}
	}

	public void compare()
	{

		for(int i = 0; i < genreList.length;i++)
		{	
			for(int l = 1; l< (genreList.length-i);l++)
			{
				if(averages[l-1] < averages[l])
				{
					double temp = averages[l-1];
					averages[l-1] = averages[l];
					averages[l] = temp;

					String temp1 = genreList[l-1];
					genreList[l-1] = genreList[l];
					genreList[l] = temp1;
				}
			}
		}
		for(int i = 0; i < genreList.length; i++)
		{
			if(averages[i] != 0.0)
			{
				ranking.add(averages[i]);
				updatedGenre.add(genreList[i]);
			}
		}
		for (int s = 0; s < ranking.size(); s++)
		{	
			if(ranking.get(s) < 1.0)
			{
				counter++;
			}
		}
		ranking.clear();
	}

	public void getMovie(String genre)
	{

		for(int i = 0; i < variables.genre.size(); i++)
		{
			if(variables.genre.get(i).indexOf(genre) != -1)
			{
				movieList.add(variables.movieName.get(i));
			}
		}
	}

	public void calculateScore(String[] genre, int rating)
	{
		if(rating != 0)
		{
			for(int i = 0; i < genre.length; i++)
			{
				for(int k = 0; k < genreList.length; k++)
				{
					if(genre[i].equalsIgnoreCase(genreList[k]) == true)
					{
						score.set(k,(score.get(k)+ rating));
						movieCount[k] = movieCount[k] +1;
					}
				}
			}
		}
	}

	public void calculateAverage()
	{
		String temp[];
		for(int i = 0; i < variables.score.size(); i++) 
		{
			int y = variables.movieName.indexOf(variables.userWatched.get(i));
			temp = variables.genre.get(y).split(" ");

			if(variables.score.get(i) != 0)
			{
				for(int k = 0; k < temp.length; k++)
				{
					for(int l = 0; l < genreList.length; l++)
					{
						if(temp[k].equalsIgnoreCase(genreList[l]) == true)
						{
							score.set(l,(score.get(l) + variables.score.get(l)));
							movieCount[l] = movieCount[l] +1;
						}
					}
				}
			}
		}
	}

	/*public static void main(String []args)
	{
		new predictedCalculations("student1449");
	}*/
}

