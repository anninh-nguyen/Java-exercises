package Exercise4;

import java.io.*;

public class Exercise4 {
    public static void main(String[] args) {

        // // Question #1
        // Exam ex  = new Exam(50);System.out.println(ex.getGrade());
        // Exam ex1 = new Exam(60);System.out.println(ex1.getGrade());
        // Exam ex2 = new Exam(25);System.out.println(ex2.getGrade());
        // Exam ex3 = new Exam(75);System.out.println(ex3.getGrade());
        // Exam ex4 = new Exam(85);System.out.println(ex4.getGrade());
        // Exam ex5 = new Exam(89);System.out.println(ex5.getGrade());
        // Exam ex6 = new Exam(90);System.out.println(ex6.getGrade());
        // Exam ex7 = new Exam(91);System.out.println(ex7.getGrade());
        // Exam ex8 = new Exam(101);System.out.println(ex8.getGrade());
        // Exam ex9 = new Exam(100);System.out.println(ex9.getGrade());
        // Exam ex10 = new Exam(0);System.out.println(ex10.getGrade());
        // Exam ex11 = new Exam(-1);System.out.println(ex11.getGrade());

        // Essay ess = new Essay(85,true,true);
        // System.out.println(ess.toString());
        // Essay ess2 = new Essay(50,true,false);
        // System.out.println(ess2.toString());
        // Essay ess3 = new Essay(75,false,false);
        // System.out.println(ess3.toString());


        // // Question #2
        // Artwork aa = new Artwork("Anonymouz","Myster-y","Scifistic Experimentalism");
        // System.out.println(aa.toString());

        // Book b = new Book("Harari","Nexus","Non-fiction prose",492);
        // System.out.println(b.toString());
            
        // Song s = new Song("Sami Yaffa","Rotten Roots","Rock",257,123);
        // System.out.println(s.toString());
        
        // Painting p = new Painting("asfd","sdfg","asdg",50000.23);
        // System.out.println(p.toString());


        // // Question #3
        // Exam ee = new Exam(51);
        // Exam ee2 = new Exam(51);
        // System.out.println(ee.equals(ee2));
        // ee2 = new Exam(50);
        // System.out.println(ee.equals(ee2));

        // Essay ee3 = new Essay(51,false,true);
        // Essay ee4 = new Essay(51,false,true);
        // System.out.println(ee3.equals(ee4));
        // ee2 = new Essay(51,false,false);
        // System.out.println(ee3.equals(ee4));
        // ee2 = new Essay(50,false,true);
        // System.out.println(ee3.equals(ee4));


        // // Question #4
        // Artwork aa = new Artwork("a","b","c");
        // Book bb = new Book("Art Author1","Art Name1","Scifi",50);
        // Song ss = new Song("Art Author2","Art Name2","Scifi1",50,100);
        // Song s2 = new Song("Art Author3","Art Name3","Scifi2",50,100);
        // Painting pp = new Painting("asfd","sdfg","asdg",50000.23);
        // ArtList al = new ArtList();
        // al.addArt(aa);
        // al.addArt(bb);
        // al.addArt(ss);
        // al.addArt(pp);
        // al.addArt(s2);
        // System.out.println(al.getNoArt());
        // System.out.println(al.getNoSong());
        // System.out.println(al.getNoBook());
        // System.out.println(al.getNoPainting());
        // al.printAuthors();


        // // Question #5
        // Bicycle bbb = new Bicycle(3);
        // bbb.setColor("Blue");
        // bbb.setTopSpeed(3.3);
        // System.out.println(bbb.toString());
        // if (bbb instanceof Vehicle) System.out.println("Yes");

        // Helicopter hhh = new Helicopter("Green",100.54,1);
        // System.out.println(hhh.toString());
        // if (hhh instanceof hasEngine) System.out.println("All");
        // if (hhh instanceof Vehicle) System.out.println("Good.");


        // Question #6
        Artwork aa = new Artwork("a","b","c");
        Book bb = new Book("Art Author1","Art Name1","Scifi",50);
        Song ss = new Song("Art Author2","Art Name2","Scifi1",50,100);
        Song s2 = new Song("Art Author3","Art Name3","Scifi2",50,100);
        Painting pp = new Painting("asfd","sdfg","asdg",50000.23);

        ArtList artList = new ArtList();

        artList.addArt(aa);
        artList.addArt(bb);
        artList.addArt(ss);
        artList.addArt(pp);
        artList.addArt(s2);

        String filePath =  new File("").getAbsolutePath() + "/bin/Exercise4/art.data";

        artList.serWrite(filePath);

        var readArtList = new ArtList();
        readArtList.serRead(filePath);

        for (Artwork artwork : readArtList.artlist){
            System.out.println(artwork.toString());
        }
    }
}
