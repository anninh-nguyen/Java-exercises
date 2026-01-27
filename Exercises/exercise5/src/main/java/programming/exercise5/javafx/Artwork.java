package programming.exercise5.javafx;

import java.io.*;

public class Artwork implements Serializable {

    private String author;
    private String name;
    private String genre;
    private String thumb;
    
    public Artwork(String author, String name, String genre){
        super();
        this.author = author;
        this.name = name;
        this.genre = genre;
    }
    
    public Artwork(String author, String name, String genre, String thumbnail ){
        super();
        this.author = author;
        this.name = name;
        this.genre = genre;
        this.thumb = thumbnail;
    }

    public String getname(){
        return this.name;
    }

    public void setname(String name){
        this.name = name;
    }

    public String getAuthor(){
        return this.author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getGenre(){
        return this.genre;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

    public String getThumbnail() {
        return this.thumb;
    }

    public String toString(){
        return "Author:" + this.author + "\n" +
                "Name:" + this.name + "\n" +
                "Genre:" + this.genre;
    }
}
