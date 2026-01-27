package Exercise3;

public class Exercise3 {
    public static void main(String[] args) {
        // Question #1
        Banker.banking();


        // Question #2
        WeatherStation ws = new WeatherStation(1.5,2.5);
        System.out.println(ws.getTemperature());
        System.out.println(ws.getPressure());
        ws.setTemperature(25.6);
        ws.setPressure(0.5);
        System.out.println(ws.getTemperature());
        System.out.println(ws.getPressure());


        // Question #3
        Fan f = new Fan();
        f.setSpeed(2);
        f.setRadius(6.0);
        f.setColor("Pink");

        Fan f2 = new Fan();
        f2.setSpeed(2);
        f2.setRadius(6.0);
        f2.setColor("Pink");

        System.out.println(f2.equals(f));
        System.out.println(f.equals(f2));

        f2.setSpeed(3);
        System.out.println(f.equals(f2));

        Fan f3 = new Fan();
        f3.setSpeed(2);
        f3.setRadius(6.0);
        f3.setColor("Pink");

        Fan f4 = new Fan();
        f4.setSpeed(2);
        f4.setRadius(6.0);
        f4.setColor("Pink");

        System.out.println(f4.fastest(f3));
        System.out.println(f3.fastest(f4));

        f4.setSpeed(3);
        System.out.println(f3.fastest(f4));
        f4.setSpeed(1);
        System.out.println(f.fastest(f4));
        f3.setSpeed(1);
        System.out.println(f.fastest(f4));


        // Question #4
        Student s = new Student(3,120,"John Student");
        Student s2= new Student(4,120,"Hello Student");
        System.out.println(s.toString());

        Student s3 = new Student(3,120,"John Student");
        Student s4= new Student(4,120,"Hello Student");
        Student s5= new Student(4,120,"Wello Student");
        System.out.println(s4.equals(s3));
        System.out.println(s3.equals(s4));
        System.out.println(s3.equals(s5));
        System.out.println(s5.equals(s4));

        Student s6 = new Student(3,120,"John Student");
        Student s7= new Student(4,120,"Hello Student");
        System.out.println(s7.avgcrHigher(s6));
        System.out.println(s6.avgcrHigher(s7));


        // Question #5
        Song sss = new Song("Bombs Away","Eels",10);
        Song sss2 = new Song("Grave Walker","Dave Holland",20);
        Song sss3 = new Song("Voi olla että voi","Asa",30);
        Player pl1 = new Player (sss,sss2,sss3);
        pl1.printAll();

        Player pl2 = new Player (new Song("Bombs Away","Eels",10),new Song("Grave Walker","Dave Holland",20),new Song("Voi olla että voi","Asa",30));
        System.out.println(Song.getNumberOfSongs());
        System.out.println(pl2.getSong2().getSongName());


        // Question #6
        Song ss = new Song("Bombs Away","Eels",10);
        Song ss2 = new Song("Grave Walker","Dave Holland",20);
        Song ss3 = new Song("Voi olla että voi","Asa",30);
        PlayList pla = new PlayList(5);
        pla.addSong(ss);
        pla.addSong(ss2);
        pla.addSong(ss3);
        pla.printAll();

        Song ss4 = new Song("Bombs Away","Eels",10);
        Song ss5 = new Song("Grave Walker","Dave Holland",20);
        Song ss6 = new Song("Voi olla että voi","Asa",30);
        PlayList pla2 = new PlayList(5);
        pla2.addSong(ss4);
        pla2.addSong(ss5);
        pla2.addSong(ss6);
        pla2.addSong(new Song("Takapajula","Maustetytöt",100));
        pla2.addSong(ss5);
        pla2.printAll();
        pla2.addSong(ss5);


        // Question #7
        Library lib = new Library(10);
        lib.addBook(new Book(new Author("Marcel",93),"Theories"));
        lib.addBook(new Book(new Author("Marco",43),"Factual Fiction"));
        lib.addBook(new Book(new Author("Marko",53),"Fictional Fact"));
        lib.inventory();

        Library lib2 = new Library(10);
        lib2.addBook(new Book(new Author("Marcel",93),"Theories"));
        lib2.addBook(new Book(new Author("Marco",43),"Factual Fiction"));
        lib2.addBook(new Book(new Author("Marko",53),"Fictional Fact"));
        lib2.addBook(new Book(new Author("Marcelo",93),"Funny Theories"));
        lib2.addBook(new Book(new Author("Marcos",43),"Factual Fiction Again"));
        lib2.addBook(new Book(new Author("Markos",53),"More Fictional Fact"));
        lib2.inventory();


        // Question#8
        WeirdChecker wr1 = new WeirdChecker("12345-12345!12345-12345!1235d5-123456!asn!66666-55555");
        wr1.printStats();

        WeirdChecker wr2 = new WeirdChecker("12345-12345!12345-12345!1235d5-123456!asn!66666-55555!12345-12345!12345-12345!1235d5-123456!asn!66666-55555");
        wr2.printStats();

        WeirdChecker wr3 = new WeirdChecker("");
        wr3.printStats();


        // Question #9
        StackOfRectangles sor1 = new StackOfRectangles();
        sor1.push(new Rectangle(3,3));
        sor1.push(new Rectangle(4,4));
        System.out.println(sor1.getTotalArea());

        StackOfRectangles sor2 = new StackOfRectangles();
        sor2.push(new Rectangle(3,3));
        sor2.push(new Rectangle(5,5));
        sor2.push(new Rectangle(7,7));
        System.out.println(sor2.getTotalArea());
        sor2.pop();
        System.out.println(sor2.getTotalArea());
        sor2.pop();
        System.out.println(sor2.getTotalArea());
        sor2.pop();
        System.out.println(sor2.getTotalArea());

        StackOfRectangles sor3 = new StackOfRectangles();
        System.out.println(sor3.isEmpty());
        sor3.push(new Rectangle(10,10));
        sor3.push(new Rectangle(0,0));
        sor3.push(new Rectangle(5,5));
        System.out.println(sor3.getTotalArea());
        System.out.println(sor3.getSize());
        System.out.println(sor3.isEmpty());
        Rectangle rrr = sor3.pop();
        System.out.println(rrr.getDiagonal());
        rrr = sor3.peek();
        System.out.println(rrr.getArea());
        System.out.println(sor3.getTotalArea());
        sor3.pop();
        System.out.println(sor3.getTotalArea());
        sor3.pop();
        System.out.println(sor3.getTotalArea());
        System.out.println(sor3.isEmpty());
        System.out.println(sor3.getSize());


        // Question #10
        MyPoint mp1  = new MyPoint(3.0,3.0,"Central Square");
        MyPoint mp2 = new MyPoint(2.0,2.0,"Railway Station");
        System.out.println(mp1.toString());
        System.out.println(mp2.toString());
        System.out.println("Distance:"+mp1.distance(mp2));

        MyPoint mp3  = new MyPoint(7.0,4.0,"Karelia UAS");
        MyPoint mp4 = new MyPoint(18.0,16.0,"UEF Science Park");
        System.out.println(mp3.toString());
        System.out.println(mp4.toString());
        System.out.println("Distance:"+mp3.distance(mp4));
    }
}
