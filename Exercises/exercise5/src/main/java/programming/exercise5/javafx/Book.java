package programming.exercise5.javafx;

public class Book extends Artwork {
    private int pages;

    public Book (String author, String name, String genre, String thumbnail, int pages){
        super(author, name, genre, thumbnail);
        this.pages = pages;
    }

    public int getNoPages () {
        return this.pages;
    }

    public String toString(){
        return super.toString() + "\nPages:" + this.pages;
    }
}
