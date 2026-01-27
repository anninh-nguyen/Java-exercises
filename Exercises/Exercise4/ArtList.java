package Exercise4;

import java.util.ArrayList;
import java.io.*;

public class ArtList {

    ArrayList<Artwork> artlist;

    public ArtList () {
        artlist = new ArrayList<Artwork>();
    }

    public void addArt(Artwork artwork) {
        this.artlist.add(artwork);
    }

    public int getNoArt(){
        return this.artlist.size();
    }

    public int getNoSong() {
        var count = 0;

        for (Artwork artwork : artlist){
            if (artwork instanceof Song){
                count++;
            }
        }

        return count;
    }

    public int getNoBook() {
        var count = 0;

        for (Artwork artwork : artlist){
            if (artwork instanceof Book){
                count++;
            }
        }

        return count;
    }

    public int getNoPainting() {
        var count = 0;

        for (Artwork artwork : artlist){
            if (artwork instanceof Painting){
                count++;
            }
        }

        return count;
    }

    public void printAuthors() {
        for (Artwork artwork : artlist){
            System.out.println(artwork.getAuthor());
        }
    }

    public void serWrite(String filePath) {
        try{
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
            outStream.writeObject(artlist);
            outStream.close();
            fileOut.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void serRead(String filePath) {
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream inStrem = new ObjectInputStream(fileIn);
            artlist = (ArrayList<Artwork>)(inStrem.readObject());
            inStrem.close();
            fileIn.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}