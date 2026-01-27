package programming.java.javafx;

import java.util.ArrayList;
import java.util.Map.Entry;

public class JD {
    public String name;
    public String title;
    public ArrayList<Entry<String, String>> requiredSkills;
    public String conclution;
    
    public JD() {}

    public JD (String name, String title, ArrayList<Entry<String, String>> requiredSkills, String conclution) {
        this.name = name;
        this.title = title;
        this.requiredSkills = requiredSkills;
        this.conclution = conclution;
    }
}
