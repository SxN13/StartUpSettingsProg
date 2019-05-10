package sample;

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class TemplateMark {
    private static SimpleStringProperty name;

    private static ArrayList<String> docTemplate = new ArrayList<>();
    private static ArrayList<String> progTemplate = new ArrayList<>();

    public TemplateMark(){
            docTemplate.add("\\documents");
            docTemplate.add("\\documents\\books");
            docTemplate.add("\\documents\\images");
            docTemplate.add("\\documents\\music");
            docTemplate.add("\\documents\\text");
            docTemplate.add("\\project");
            docTemplate.add("\\project\\code");
            docTemplate.add("\\project\\models");
            docTemplate.add("\\project\\images");
            docTemplate.add("\\tmp");
            progTemplate.add("\\downloads");
            progTemplate.add("\\downloads\\instalations");
            progTemplate.add("\\downloads\\video");
            progTemplate.add("\\downloads\\films");
            progTemplate.add("\\downloads\\lessons");
            progTemplate.add("\\programs");
            progTemplate.add("\\programs\\links");
            progTemplate.add("\\programs\\software");
            progTemplate.add("\\programs\\games");
            progTemplate.add("\\programs\\links\\browser");
            progTemplate.add("\\programs\\links\\IDE");
            progTemplate.add("\\programs\\links\\graphics");
            progTemplate.add("\\programs\\links\\office");
            progTemplate.add("\\programs\\links\\media");
            progTemplate.add("\\programs\\links\\utils");
            progTemplate.add("\\programs\\links\\virtualization");
            progTemplate.add("\\programs\\links\\other");
            progTemplate.add("\\tmp");
        }

    public ArrayList<String> getDocTemplate() {
        return docTemplate;
    }

    public ArrayList<String> getProgTemplate() {
        return progTemplate;
    }


}
