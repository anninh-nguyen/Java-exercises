package Exercise3;

public class Student {
	private int yearsStudied;
	private int credits; 
	private String name;
	
	public Student(){
		this(0,0,"Unknown");
	}
	public Student(int yrs,int crd,String nm){
		yearsStudied = yrs;
		credits = crd;
		name = nm;
	}
	public String getName(){
		return name;
	}
	public double getAverageCreditsPerYear(){
		if (yearsStudied == 0)
			return -1;
		return (double)credits/yearsStudied;
	}
	public int getYrsStudied() { return yearsStudied; }
	public int getCredits() { return credits; }

    public String toString(){
        return this.name + " Cr:" + this.credits + " Ys:" + this.yearsStudied;
    }

    public boolean equals(Student student){
        if (this.yearsStudied == student.yearsStudied && this.credits == student.credits)
            return true;

        return false;
    }

    public boolean avgcrHigher(Student student){
        if (this.getAverageCreditsPerYear() < student.getAverageCreditsPerYear())
            return true;
        return false;
    }

    /*
    (1) add a public method toString, which will return a String representation of the object 
    in the following (example) format: "John Student Cr:120 Ys:3"

    (2) add a public method equals, which takes as its parameter another Student object. 
    The method will return true, if the years studied and number of credits are exactly the same 
    between this object and the parameter object (the name can differ). Otherwise, the method will return false. 

    (3) add a public method avgcrHigher, which takes as its parameter another Student object. 
    The method will return true, if the average credits per year is higher in the parameter object. 
    Otherwise, the method will return false. 
     */
}