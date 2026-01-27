package programming.exercise5.javafx;

public class Song extends Artwork {
    public int seconds;
    public int timesPlayed;

    public Song(String author, String name, String genre, String thumbnail, int seconds, int timesPlayed){
        super(author, name, genre, thumbnail);
        this.seconds = seconds;
        this.timesPlayed = timesPlayed;
    }

    public int getSeconds () {
        return seconds;
    }

    public int getTimeplayed () {
        return timesPlayed;
    }

    public String toString(){
        return super.toString() + "\n" +
                "Seconds:" + this.seconds + "\n" +
                "Times played:" + this.timesPlayed;
    }
}
