package Exercise2;

public class Song{
    public String Name;
    public String Author;
    public int Number;

    public Song ()
    {
        Name = "No song";
        Author = "No author";
        Number = 0;
    }

    public Song (String pName, String pAuthor, int pNumber)
    {
        Name = pName;
        Author = pAuthor;
        Number = pNumber;
    }

    public String getSongName() {
        return Name;
    }

    public void setSongName(String newName) {
        this.Name = newName;
    }

    public String getAuthorName() {
        return Author;
    }

    public void setAuthorName(String newAuthor) {
        this.Author = newAuthor;
    }

    public int getTimes() {
        return Number;
    }

    public void setTimes(int newNumber) {
        this.Number = newNumber;
    }
}