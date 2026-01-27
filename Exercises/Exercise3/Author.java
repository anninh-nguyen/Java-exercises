package Exercise3;

public class Author {
	private String authorName;
	private int authorAge;
	
	public Author(String authorName, int authorAge){
		this.authorName = authorName;
		this.authorAge = authorAge;
	}
	public Author(){   this("Noname",0);   }
	
	public String getName(){  return authorName;  }

    public int getAuthorAge() { return authorAge;}
}