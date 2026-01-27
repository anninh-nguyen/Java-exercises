package Exercise3;

public class Song {
	private String songName;
	private String authorName;
	private int times;
    private static int numberOfSongs_ob = 0;
	
	public Song(){
		songName = "No song";
		authorName = "No author";
		times = 0;
        numberOfSongs_ob++;
	}
	public Song(String sn, String an, int tim){
		songName = sn; authorName = an; times = tim;
        numberOfSongs_ob++;
	}
	public String getSongName(){
		return songName;
	}
	public void setSongName(String sn){
		songName = sn;
	}
	public String getAuthorName(){
		return authorName;
	}
	public void setAuthorName(String an){
		authorName = an;
	}
	public int getTimes(){
		return times;
	}
	public void setTimes(int ti){
		times = ti;
	}
    public static int getNumberOfSongs()
    {
        return numberOfSongs_ob;
    }
}