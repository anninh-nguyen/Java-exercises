package Exercise3;

public class Book {
	private Author bookAuthor;
	private String bookName;
	
	public Book(Author author, String name){
		bookAuthor = author;
		bookName = name;
	}
	public String getName()  { return bookName; }

	public String getAuthorName(){ 
		return bookAuthor.getName();
	}

    public int getAuthorAge() { return bookAuthor.getAuthorAge();}
}