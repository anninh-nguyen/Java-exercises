package Exercise1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) throws Exception
    {
        try
        {
            Print();


            PrintWithLineBreak("Hello", "world!");
            PrintWithoutLineBreak("Hello", "world!");
            

            // Testing the Average-method
            System.out.println(Average(1.23, 6.35, 34.2));
            System.out.println(Average(6.2, 2.1, 3.7));
            System.out.println(Average(3.4, 6.23, 5));
            System.out.println(Average(1,2,3));

            // Testing the Sum-method
            System.out.println(Sum(5,12,6,2));
            System.out.println(Sum(1,2,3,4));
            System.out.println(Sum(5,10,20,200));
            System.out.println(Sum(6,98,100,60));


            EvenNumberOfLetters("Testing...");
            EvenNumberOfLetters("Testing a bit more!");
            EvenNumberOfLetters("And once more!");
            EvenNumberOfLetters("Couldn't hurt to test one more time");
            // lets test the empty string
            EvenNumberOfLetters("");


            System.out.println(Quadrant(5, 6));
            System.out.println(Quadrant(-5, 3));
            System.out.println(Quadrant(-25, -36));
            System.out.println(Quadrant(25, -336));
            System.out.println(Quadrant(-25, 0));
            System.out.println(Quadrant(0, 230));
            System.out.println(Quadrant(0, 0));

            System.out.println(Quadrant(-25, -36));
            System.out.println(Quadrant(-25, 336));
            System.out.println(Quadrant(-25, 0));


            System.out.println(ConvertToInteger("51"));
            System.out.println(ConvertToInteger("Hello world!"));
            System.out.println(ConvertToInteger("10.5"));

            System.out.println(ConvertToDouble("51.23"));
            System.out.println(ConvertToDouble("Hello World!"));
            System.out.println(ConvertToDouble("12"));


            System.out.println(howManyYWD(500));

            System.out.println(howManyYWD(600));

            System.out.println(howManyYWD(700));


            System.out.println("Length of the array:"+ numbers.length);
            // printing the items
            for(int i = 0; i < numbers.length; i++){
                System.out.println(numbers[i]);
            }

            System.out.println("Length of the array:"+ letters.length);
            // printing the items
            for(int i = 0; i < letters.length; i++){
                System.out.println(letters[i]);
            }


            // Testing the For loop
            Loops(5,0);
            // Testing the While loop
            Loops(0, 5);
            // Testataan molemmat silmukat
            Loops(10,2);
            // Testing both loops at the same time
            Loops(5,9);


            PrintFirst(new int[]{1,2,3,4,5,6,7,8,9,10});
            PrintFirst(new int[]{3,6,12,4});
            PrintFirst(new int[]{7,3,78,1,3});
            PrintFirst(new int[]{9,8,7,6,5,4,3,2,1});

            PrintLast(new int[]{1,2,3,4,5,6,7,8,9,10});
            PrintLast(new int[]{3,6,12,4});
            PrintLast(new int[]{7,3,78,1,3});
            PrintLast(new int[]{9,8,7,6,5,4,3,2,1});

            PrintArray(new int[]{1,2,3,4,5,6,7,8,9,10});
            PrintArray(new int[]{3,6,12,4});
            PrintArray(new int[]{7,3,78,1,3});
            PrintArray(new int[]{9,8,7,6,5,4,3,2,1});


            NestedLoop("Hei",3);
            NestedLoop("Welcome",10);


            System.out.println(checkLottery(new int[]{1,2,3,4,5,6,7},new int[]{13,12,11,10,9,8,7}));
            System.out.println(checkLottery(new int[]{7,6,5,4,3,2,1},new int[]{1,3,2,5,4,80,7}));
            System.out.println(checkLottery(new int[]{1,2,3,4,5,6,7},new int[]{2,17,3,15,9,12,1}));
                
            System.out.println(checkLottery(new int[]{1,2,3,4,5,6,7},new int[]{13,12,11,10,9,8,70}));
            System.out.println(checkLottery(new int[]{12,25,23,24,1,2,3},new int[]{25,12,1,2,23,24,3}));

            System.out.println(checkLottery(new int[]{1,2,3,4,5,6,7},new int[]{13,12,11,10,9,8,7}));


            RepeatPrintOut("Hello World!", 5);

            RepeatPrintOut("ten", 10);
            int a = 2;
            int b = 5;
            int c = 7;
            int d = 4;
            
            System.out.println("Greatest of four...");
            System.out.println("Numbers:");
            System.out.println(String.format("a:%s, b:%s, c:%s, d:%s", a, b, c, d));
            System.out.println(String.format("Your method returned:"));
            System.out.println(GreatestOfFour(a, b, c, d));

            a = 10;
            b = 6;
            c = 8;
            d = 9;
            System.out.println("Greatest of four...");
            System.out.println("Numbers:");
            System.out.println(String.format("a:%s, b:%s, c:%s, d:%s", a, b, c, d));
            System.out.println(String.format("Your method returned:"));
            System.out.println(GreatestOfFour(a, b, c, d));

            a = 1;
            b = 2;
            c = 3;
            d = 4;
            System.out.println("Greatest of four...");
            System.out.println("Numbers:");
            System.out.println(String.format("a:%s, b:%s, c:%s, d:%s", a, b, c, d));
            System.out.println(String.format("Your method returned:"));
            System.out.println(GreatestOfFour(a, b, c, d));

            System.out.println("Testing the Shrink method.");
            float initial = 5;
            float limit = 1;
            float step = 0.25f;
            System.out.println(String.format("Initial:%s, Limit:%s, Step:%s", initial, limit, step));
            System.out.println(String.format("Result: %s", Shrink(initial, limit, step)));

            System.out.println("Testing the Shrink method.");
            initial = 8;
            limit = 2;
            step = 0.45f;
            System.out.println(String.format("Initial:%s, Limit:%s, Step:%s", initial, limit, step));
            System.out.println(String.format("Result: %s", Shrink(initial, limit, step)));


            ReadToScreen("testi1.txt");

            ReadToScreen("testi2.txt");

            ReadToScreen("non_existing_file.txt");
        }
        catch (Exception e)
        {
            System.out.println(e.getStackTrace());
        }
    }


    // Answer
    public static void Print()
    {
        /* Complete the exercise here, below this comment!*/
        System.out.println("Hello World!");
    }

    // Question2 
    // the keyword void means that the method does not have a return value
    public static void PrintWithLineBreak(String a, String b)
    {
        System.out.println(a + '\n' + b);
    }

    public static void PrintWithoutLineBreak(String a, String b)
    {
        System.out.println(a + b);
    }

    // Question3 
    public static double Average (double a, double b, double c)
    {
        return (a + b + c) / 3;
    }

    public static int Sum (int a, int b, int c, int d)
    {
        return (a + b + c + d);
    }

    // Question4
    public static void EvenNumberOfLetters(String text)
    {
        System.out.println("text: " + text);
        int strLength = text.length();
        if (strLength == 0)
        {
            System.out.println("Empty string!");
        }
        else 
        {
            if (strLength%2 == 0)
            {
                System.out.println("Even number of letters!");
            }
            else
            {
                System.out.println("Odd number of letters!");
            }
        }
    }

    // Question5
    public static int Quadrant(int x, int y)
    {
        if (x == 0 || y == 0)
            return 0;
        if (x > 0)
            if (y > 0)
                return 1;
            else
                return 4;
        else
            if (y > 0)
                return 2;
            else
                return 3;
    }

    // Question6
    public static int ConvertToInteger(String s)
    {
        try {
            return Integer.parseInt(s);

        }catch (Exception E){
            // Leave this command as is:
            System.out.println(E);
            // Write code here:
            return 0;
        }
        
    }

    public static double ConvertToDouble(String s)
    {
        try {
            return Double.parseDouble(s);
            
        }catch (Exception E){
            // Leave this command as is:
            System.out.println(E);
            // Write code here:
            return 0.0;
        }
        
    }

    // Question7
    public static String howManyYWD(int days){
        int yearCount = days/365;
        int weekCount = (days - 365*yearCount)/7;
        int dayCount = (days - 365*yearCount - weekCount*7) % 7;
        return days + " days equals " + yearCount +" years, " + weekCount + " weeks and " + dayCount + " days.";
    }

    // Question8
    static int[] numbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    static char[] letters = new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g'};


    // Question9
    public static void Loops(int forLoopMaxIterations, int whileLoopMaxIterations)
    {
        for (int i = 0; i < forLoopMaxIterations; i++)
        {
            System.out.println("For loop: " + i);
        }
        int i = 0;
        while (i < whileLoopMaxIterations)
        {
            System.out.println("While loop: " + i);
            i++;
            
        }
    }

    // Question10
    public static void PrintFirst(int[] myArray)
    {
        System.out.println(myArray[0]);
    }

    public static void PrintLast(int[] myArray)
    {
        System.out.println(myArray[myArray.length - 1]);
    }

    public static void PrintArray(int[] myArray)
    {
        for (int i = 0; i < myArray.length; i++)
        System.out.println(myArray[i]);
    }

    // Question11
    public static void NestedLoop(String s, int upper_limit)
    {
        for (int i = 0; i < s.length(); i++)
            for (int j = 0; j <= upper_limit; j++)
                System.out.println(s.charAt(i) + "-" + j);
    }

    // Question12{
    public static int checkLottery(int[] correct, int[] guess)
    {
        int correctCount = 0;
        for (int i = 0; i < correct.length; i++)
            for (int j = 0; j < guess.length; j++)
                if (guess[j] == correct[i])
                    correctCount ++;
        return correctCount;
    }

    // Question13
    public static void RepeatPrintOut(String a, int n)
    {
        for (int i = 0; i < n; i++)
            System.out.println(a);
    }

    public static int GreatestOfFour(int a, int b, int c, int d)
    {
        int[] gang = new int[]{a, b, c, d};
        int greatest = gang[0];
        for (int i = 1; i< 4; i++)
            if (greatest < gang[i])
                greatest = gang[i];
        return greatest;
    }

    public static float Shrink(float initial, float limit, float step)
    {
        while (initial > limit)
        {
            initial = initial - step;
        }
        return initial;
    }

    // FileExercise 
    public static void ReadToScreen(String fileName)
    {
        try
        {   
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine())
            {
                String data = myReader.nextLine();
                data.split(",");
                System.out.println(data);
                
            }
            myReader.close();
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("File is not found!");
        }
    }
}