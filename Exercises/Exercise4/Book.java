package Exercise4;

public class Book extends Artwork {
    private int pages;

    public Book (String author, String name, String genre, int pages){
        super(author, name, genre);
        this.pages = pages;
    }

    public String toString(){
        return super.toString() + "\nPages:" + this.pages;
    }
}
