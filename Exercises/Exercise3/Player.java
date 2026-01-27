package Exercise3;

public class Player {

    private Song song1;
    private Song song2;
    private Song song3;

    public Player(Song song1, Song song2, Song song3){
        this.song1 = song1;
        this.song2 = song2;
        this.song3 = song3;
    }

    public Song getSong1(){
        return this.song1;
    }

    public Song getSong2(){
        return this.song2;
    }

    public Song getSong3(){
        return this.song3;
    }

    public void printAll(){
        System.out.println("(1) " + song1.getAuthorName() + " : " + song1.getSongName() + " (" + song1.getTimes() + ")");
        System.out.println("(2) " + song2.getAuthorName() + " : " + song2.getSongName() + " (" + song2.getTimes() + ")");
        System.out.println("(3) " + song3.getAuthorName() + " : " + song3.getSongName() + " (" + song3.getTimes() + ")");
    }
}