package Exercise3;

public class Library {
	private Book[] books;
	private int numberofBooks;
	
	public Library(int maxNumberOfBooks) {
		books = new Book[maxNumberOfBooks];
		numberofBooks = 0;
	}
	public void addBook(Book b) {
		books[numberofBooks] = b;
		numberofBooks += 1;
	}
	public Book[] getAllBooks() { return books; }

	public void inventory(){
		for (int i = 0; i < books.length; i++){
			if(books[i] == null){
				continue;
			}

			System.out.println( (i + 1) + ". Author: " + books[i].getAuthorName() + 
			" (" + books[i].getAuthorAge() + ") Book: " + books[i].getName());
		}
	}
}