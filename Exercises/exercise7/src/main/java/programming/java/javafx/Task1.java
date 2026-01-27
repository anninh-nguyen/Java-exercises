package programming.java.javafx;

import java.io.File;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Task1 extends Application {

    VBox jobListVBox = new VBox();
    Text titleText = new Text();
    VBox requireSkillVBox = new VBox();
    TextArea skillDescArea = new TextArea();
    TextArea finalGoal = new TextArea("");

    String jobSelected = "";
    ArrayList<String> skillsSelected = new ArrayList<String>();

    @Override
    public void start(Stage stage) throws Exception {

        jobListVBox.setSpacing(30);
        jobListVBox.getChildren().add(new Text("Skills to development"));
        jobListVBox.setPrefWidth(150);
        skillDescArea.setWrapText(true);
        requireSkillVBox.setSpacing(30);
        requireSkillVBox.setPrefWidth(300);
        makeJobList(xmlParse());
        
        HBox main = new HBox();
        main.getChildren().addAll(jobListVBox, titleText, requireSkillVBox, skillDescArea);

        Button accepBT = new Button("Set new goal");
        accepBT.setOnAction(acceptClick -> {
            if (jobSelected == "" || (skillsSelected.size() == 0) ) { return; }

            finalGoal.setText("");
            finalGoal.setText("Development goal 2025 for " + jobSelected + " will be: \n");
            for (String skillSelected : skillsSelected)
            finalGoal.setText(finalGoal.getText() + "  - " + skillSelected + "\n");
        });

        finalGoal.setWrapText(true);
        
        HBox acceptHBox = new HBox();
        acceptHBox.setSpacing(30);
        acceptHBox.getChildren().addAll(accepBT, finalGoal);

        VBox root = new VBox();
        root.getChildren().addAll(main, acceptHBox);
        root.setSpacing(30);
        root.setPadding(new Insets(30));
        Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Development skills in " + Calendar.getInstance().get(Calendar.YEAR));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private ArrayList<JD> xmlParse() throws Exception {
        try {
            // Specify the file path as a File object
            File xmlFile = new File("jd.xml");

            // Create a DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML file
            Document document = builder.parse(xmlFile);

            ArrayList<JD> jds = new ArrayList<>();

            NodeList nodeList 
                = document.getElementsByTagName("postion"); 

            for (int i = 0; i < nodeList.getLength(); i++){
                Node positionNode = nodeList.item(i);
                if (positionNode.getNodeType() == Node.ELEMENT_NODE) { 
                    Element tElement = (Element)positionNode; 
                    
                    var jd = new JD();
                    jd.name = tElement.getElementsByTagName("name").item(0).getTextContent();
                    jd.title = tElement.getElementsByTagName("title").item(0).getTextContent();

                    jd.requiredSkills = new ArrayList<Entry<String, String>>();
                    var requiredSkillNodeList = tElement.getElementsByTagName("requiredSkills");
                    for(int j = 0; j < requiredSkillNodeList.getLength(); j++){
                        Node requireSkillNode = requiredSkillNodeList.item(j);
                        if (requireSkillNode.getNodeType() == Node.ELEMENT_NODE) { 
                            Element requiedSkillElement = (Element)requireSkillNode; 
                            requiedSkillElement.getElementsByTagName("skillDescription");
                            Map.Entry<String,String> skillEntry =
                            new AbstractMap.SimpleEntry<String, String>(
                                requiedSkillElement.getElementsByTagName("skillName").item(0).getTextContent(),
                                requiedSkillElement.getElementsByTagName("skillDescription").item(0).getTextContent()
                            );
                            jd.requiredSkills.add(skillEntry);
                        }
                    }
                    jd.conclution = tElement.getElementsByTagName("conclution").item(0).getTextContent();
                    
                    jds.add(jd);
                }
            }
            
            return jds;
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    private void makeJobList(ArrayList<JD> jdList){
        ToggleGroup toggleGroup = new ToggleGroup();
        for (int i = 0; i < jdList.size(); i++) {
            // get jobs
            var jd = jdList.get(i);
            RadioButton radioJob = new RadioButton(jd.name);
            radioJob.setToggleGroup(toggleGroup);

            // get required skills
            radioJob.setOnAction(rde -> { 
                // clear previous selected
                skillsSelected.clear();
                finalGoal.setText("");
                jobSelected = jd.name;
                skillDescArea.setText("");
                requireSkillVBox.getChildren().clear();

                titleText.setText(jd.title);
                requireSkillVBox.getChildren().add(titleText);
                for (int j = 0; j < jd.requiredSkills.size(); j++){
                    var skill = jd.requiredSkills.get(j);
                    CheckBox cbSkill = new CheckBox(skill.getKey());
                    cbSkill.setUserData(skill.getValue());
                    cbSkill.setOnMouseEntered(cbe -> {
                        skillDescArea.setText(cbSkill.getUserData().toString());
                    });
                    cbSkill.setOnAction(cbclick -> {
                        skillsSelected.add(cbSkill.getText());
                    });
                    requireSkillVBox.getChildren().add(cbSkill);
                }
                finalGoal.setText(jd.conclution);
            });
            
            jobListVBox.getChildren().add(radioJob);
        }
    }
}
