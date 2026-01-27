package Exercise4;

public class Essay extends Exam {
    private boolean creativity;
    private boolean storyline;

    public Essay (int totalpoints, boolean creativity, boolean storyline){
        super(totalpoints);
        this.creativity = creativity;
        this.storyline = storyline;
    }

    public boolean getCreativity(){
        return this.creativity;
    }

    public boolean getStoryline(){
        return this.storyline;
    }

    public String toString(){
        var returnedString = super.toString();
        if (creativity){
                returnedString += "Special bonus for creativity.";
            if (storyline){
                returnedString += "\n";
            }
        }
        if (storyline) {
            returnedString += "Special bonus for storyline.";
        }

        return returnedString;
    }

    public boolean equals(Essay essay){
        return super.getPoints() == essay.getPoints() &&
                this.creativity == essay.getCreativity() &&
                this.storyline == essay.getStoryline();
    }
}