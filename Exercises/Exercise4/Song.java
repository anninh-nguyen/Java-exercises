package Exercise4;

public class Song extends Artwork {
    public int seconds;
    public int timesPlayed;

    public Song(String author, String name, String genre, int seconds, int timesPlayed){
        super(author, name, genre);
        this.seconds = seconds;
        this.timesPlayed = timesPlayed;
    }

    public String toString(){
        return super.toString() + "\n" +
                "Seconds:" + this.seconds + "\n" +
                "Times played:" + this.timesPlayed;
    }
}
