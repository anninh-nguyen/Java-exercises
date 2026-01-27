package Exercise2;

public class Exercise2 {
    public static void main(String[] args) {
        
        // Question #1
        FileExercise file = new FileExercise();
        System.out.println(file.getWord("dictionary.txt","English"));
        System.out.println(file.getWord("dictionary.txt","sPaNIsH"));
        System.out.println(file.getWord("ductionaryyy.txt","sPaNIsH"));
        System.out.println(file.getWord("dictionary.txt","Portuguese"));


        // Question #2
        Song s1 = new Song("Get Lucky","Daft Punk",500);
        System.out.println("Favorite song:" + s1.getSongName());
        System.out.println("Author:" + s1.getAuthorName());
        System.out.println("Times played:" + s1.getTimes());

        Song s2 = new Song();
        System.out.println("Favorite song:" + s2.getSongName());
        System.out.println("Author:" + s2.getAuthorName());
        System.out.println("Times played:" + s2.getTimes());

        Song s3 = new Song();
        s3.setSongName("Chase");
        s3.setAuthorName("Giorgio Moroder");
        s3.setTimes(50000);
        System.out.println("Favorite song:" + s3.getSongName());
        System.out.println("Author:" + s3.getAuthorName());
        System.out.println("Times played:" + s3.getTimes());


        // Question #3
        Student stu1 = new Student();
        double d1 = stu1.getAverageCreditsPerYear();
        System.out.println(stu1.getName());
        System.out.println(d1);

        Student stu2 = new Student(3,100,"Sam Student");
        double d2 = stu2.getAverageCreditsPerYear();
        System.out.println(stu2.getName());
        System.out.println(d2);


        // Question #4
        Stock st1 = new Stock("AA","Nokia",100,50);
        System.out.println(st1.getChange());

        Stock st2 = new Stock("BB","Perry",10.10,5.5);
        System.out.println(st2.getChange());
        System.out.println(st2.getNoS());
        Stock st3 = new Stock("CC","Banana Oy",101.10,15.5);
        System.out.println(st3.getNoS());
        Stock st4 = new Stock("DD-3","Abc Oy",553,155);
        System.out.println(st4.getNoS());
        System.out.println(st4.getNoS());


        // Question #5
        Rectangle r1 = new Rectangle(4,3);
        System.out.println(r1.getDiagonal());
        System.out.println(r1.getArea());

        Rectangle r2 = new Rectangle(5);
        System.out.println(r2.getDiagonal());
        System.out.println(r2.getArea());

        Rectangle r3 = new Rectangle();
        System.out.println(r3.getDiagonal());
        System.out.println(r3.getArea());
        

        // Question #6
        Fan f1 = new Fan();
        System.out.println(f1.getSpeed());
        System.out.println(f1.getRadius());
        System.out.println(f1.getColor());
        f1.setSpeed(2);
        f1.setRadius(6.0);
        f1.setColor("Pink");
        System.out.println(f1.getSpeed());
        System.out.println(f1.getRadius());
        System.out.println(f1.getColor());

        Fan f2 = new Fan();
        f2.increaseSpeed();
        f2.increaseSpeed();
        f2.increaseSpeed();
        System.out.println(f2.getSpeed());
        f2.setSpeed(1);
        f2.decreaseSpeed();
        System.out.println(f2.getSpeed());

        Fan f3 = new Fan();
        System.out.println(f3.toString());
        f3.setSpeed(2);
        f3.setRadius(6.0);
        f3.setColor("Pink");
        System.out.println(f3.toString());
        f3.turnOn();
        System.out.println(f3.toString());
        f3.increaseSpeed();
        System.out.println(f3.toString());
        f3.turnOff();
        f3.setColor("Black");
        System.out.println(f3.toString());
    }
}