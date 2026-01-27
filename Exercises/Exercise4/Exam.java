package Exercise4;

public class Exam {
    private int points;

    public Exam (int points){
        super();
        this.points = points;
    }

    public int getPoints(){
        return this.points;
    }

    public void setPoints(int points){
        this.points = points;
    }

    public int getGrade(){
        var returnedGrade = -1;

        if (this.points < 0 || this.points > 100){
            returnedGrade = -1;
        }

        else if (this.points >= 90 && this.points <= 100){
            returnedGrade = 5;
        }

        else if (this.points >= 80){
            returnedGrade = 4;
        }

        else if (this.points >= 70){
            returnedGrade = 3;
        }

        else if (this.points >= 60){
            returnedGrade = 2;
        }

        else if (this.points >= 50){
            returnedGrade = 1;
        }
        
        else if (this.points >= 0){
            returnedGrade = 0;
        }

        return returnedGrade;
    }

    public String toString(){
        return "Exam points:" + this.points+ "\n" +
                "Exam grade:" + getGrade();
    }

    public boolean equals(Exam exam){
        return this.points == exam.getPoints();
    }
}
