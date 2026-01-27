package Exercise4;

public class Painting extends Artwork {
    private double price;

    public Painting (String author, String name, String genre, double price){
        super(author, name, genre);
        this.price = price;
    }

    public String toString(){
        return super.toString() + "\n" +
        "Price:" + this.price;
    }
}
