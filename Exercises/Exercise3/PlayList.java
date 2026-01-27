package Exercise3;

public class PlayList {
    private Song[] songs;
    private int numberOfSongs;

    public PlayList(int maxsize){
        numberOfSongs = 0;
        songs = new Song[maxsize];
    }

    public void addSong(Song s){
        if (numberOfSongs < songs.length)
        {
            for(int i = 0; i < songs.length; i++){
                if (songs[i] == null){
                    songs[i] = s;
                    numberOfSongs++;
                    break;
                }
            }
        }
        else
        {
            System.out.println("PLAYLIST IS FULL");
        }
    }
    public void printAll(){
        for(int i = 0; i < songs.length; i++){
            if (songs[i] != null){
                System.out.println("(" + (i + 1) + ") " + songs[i].getAuthorName() + " : " + songs[i].getSongName() + " (" + songs[i].getTimes() +  ")");
            }
        }
    }
}
