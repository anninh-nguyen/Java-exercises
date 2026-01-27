package Exercise2;

import java.io.File;
import java.util.Scanner;

public class FileExercise {
    public String getWord(String fileName, String language)
    {
        String executionPath = new File("").getAbsolutePath() + "/bin/Exercise2/";
        String result = "";
        // Write your code here
        File file;
        Scanner scanner;
        try {

            file = new File(executionPath + fileName);
            scanner = new Scanner(file);
            var wordFound = false;
            while (scanner.hasNextLine())
            {
                var line = scanner.nextLine();
                var data = line.split(",");
                if (data[1].toLowerCase().equals(language.toLowerCase())) {
                    result = data[0];
                    wordFound = true;
                    break;
                }
            }
            scanner.close();
            if (!wordFound){
                result = "WORD NOT FOUND.";
            }
            return result;
        }
        catch (Exception e) {
            result = "FILE NOT FOUND!";
            return result;
        }        
    }
}
