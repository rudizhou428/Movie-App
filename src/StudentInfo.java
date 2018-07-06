import java.util.ArrayList;
public class StudentInfo {
	
	private ArrayList<String> movieName = new ArrayList();
	private ArrayList<String> studentNumber = new ArrayList();
	private ArrayList<Integer> score = new ArrayList();
	
	public void printInfo()
	{
		for(String i : movieName)
		{
			System.out.println(i);
		}
	}
	
	
	
	
		
	public void setMovieName(ArrayList<String> movieName) {
		this.movieName = movieName;
	}


	public void setStudentNumber(ArrayList<String> studentNumber) {
		this.studentNumber = studentNumber;
	}


	public void setScore(ArrayList<Integer> score) {
		this.score = score;
	}
}
