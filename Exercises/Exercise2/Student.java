package Exercise2;

public class Student {
	private int yearsStudied;
	private int credits; 
	private String name;

    public Student()
    {
        yearsStudied = 0;
        credits = 0;
        name = "Unknown";
    }

    public Student(int YearStudied, int Credits, String Name)
    {
        yearsStudied = YearStudied;
        credits = Credits;
        name = Name;
    }
	
	public String getName(){
        return name;
	}

    public double getAverageCreditsPerYear()
    {
        double average = -1.0;
        if (yearsStudied >= 0 && credits > 0)
        {
            average = credits / yearsStudied;
        }
        return average;
    }
}
