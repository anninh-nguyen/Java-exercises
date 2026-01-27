package programming.exercise5.javafx;

public class Painting extends Artwork {
    private double price;

    public Painting (String author, String name, String genre, String thumbnail, double price){
        super(author, name, genre, thumbnail);
        this.price = price;
    }

    public double getPrice () {
        return this.price;
    }

    public String toString(){
        return super.toString() + "\n" +
        "Price:" + this.price;
    }
}
