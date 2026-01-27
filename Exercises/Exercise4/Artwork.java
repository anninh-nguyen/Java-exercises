package Exercise4;

import java.io.*;

public class Artwork implements Serializable {

    private String author;
    private String name;
    private String genre;
    
    public Artwork(String author, String name, String genre ){
        super();
        this.author = author;
        this.name = name;
        this.genre = genre;
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

    public String toString(){
        return "Author:" + this.author + "\n" +
                "Name:" + this.name + "\n" +
                "Genre:" + this.genre;
    }
}
