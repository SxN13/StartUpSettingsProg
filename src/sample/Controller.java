package sample;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class Controller {

    @FXML
    private TableView<DiskElement> tableView;

    @FXML
    private TableColumn<TemplateMark, String> templateColumn;

    @FXML
    private TableColumn<DiskElement, String> diskColumn;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="spMDL"
    private AnchorPane spMDL; // Value injected by FXMLLoader

    @FXML // fx:id="menuBar"
    private MenuBar menuBar; // Value injected by FXMLLoader

    @FXML // fx:id="spMD"
    private SplitPane spMD; // Value injected by FXMLLoader

    @FXML // fx:id="updateListButton"
    private Button updateListButton; // Value injected by FXMLLoader

    @FXML // fx:id="programSetupTab"
    private Tab programSetupTab; // Value injected by FXMLLoader

    @FXML // fx:id="spMDR"
    private AnchorPane spMDR; // Value injected by FXMLLoader

    @FXML // fx:id="setupList"
    private ListView<ListElement> setupList; // Value injected by FXMLLoader

    @FXML // fx:id="closeButton"
    private MenuItem closeButton; // Value injected by FXMLLoader

    @FXML // fx:id="markDiskTab"
    private Tab markDiskTab; // Value injected by FXMLLoader

    @FXML // fx:id="setupButton"
    private Button setupButton; // Value injected by FXMLLoader

    @FXML
    private Button setupButton1; // Value injected by FXMLLoader

    @FXML // fx:id="propView"
    private TextArea propView; // Value injected by FXMLLoader

    @FXML
    private TextArea logList;

    @FXML
    private TabPane tabPane;

    @FXML
    private Button markDiskButton;

    private String link = "", path = "./links/";

    private String[] checker;

    TemplateMark templateMark = new TemplateMark();

    @FXML
    void initialize() {

        closeButton.setOnAction(event -> {
            logList.appendText("Close bottom pressed");
            Platform.exit();
        });

        ////////////SETUP PROGRAM TAB////////////
        setupList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        logList.appendText("start\n");
        propView.setWrapText(true);

        fillSetupList();
        setupList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ListElement>() {
            @Override
            public void changed(ObservableValue<? extends ListElement> observable, ListElement oldValue, ListElement newValue) {
                link = newValue.getPath();
                logList.appendText(link + "\n");
                propView.clear();

                propView.appendText("File name: " + newValue.getName() + "\n");
                propView.appendText("File size: " + newValue.getSize() + " Kb\n");
                propView.appendText("Full path: " + newValue.getPath() + "\n");
            }
        });
        setupButton.setOnAction(event -> {
            try {
                launchEXE(link);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        updateListButton.setOnAction(event -> {
            System.out.println("List updated");
            logList.appendText("List updated" + "\n");
            clearSetupList();
            fillSetupList();
        });

        ////////////////////////////////////////
        ////////////MARKING DISK TAB////////////


        ObservableList<String> tempList = FXCollections.observableArrayList();
        tempList.add(0, "do not mark");
        tempList.add(1, "programs");
        tempList.add(1, "documents");

        //ChoiceBox<String> choiceBox = new ChoiceBox<>(tempList);

        diskColumn.setCellValueFactory(new PropertyValueFactory<>("diskName"));
        ObservableList<DiskElement> listDisk = FXCollections.observableArrayList(PW.findDisks());
        if (listDisk.size() > 0){
            tableView.setItems(listDisk);
            logList.appendText("Disk list OK!\nFound " + String.valueOf(listDisk.size()) + " disks\n");
        }

        checker = new String[listDisk.size()];

        templateColumn.setCellFactory(param -> {
            TableCell<TemplateMark, String> tableCell = new TableCell<>();
            final ComboBox<String> comboBox = new ComboBox<>(tempList);
            comboBox.setPromptText("Select template");

            comboBox.setOnAction(event -> {
                tableCell.getIndex();
                logList.appendText(tableCell.getIndex() + comboBox.getValue() + "\n");
                checker[tableCell.getIndex()] = comboBox.getValue();
            });

            tableCell.graphicProperty().bind(Bindings.when(tableCell.emptyProperty()).then((Node) null).otherwise(comboBox));
            return tableCell;
        });

        markDiskButton.setOnAction(event -> {
            markDisk();
        });
        ////////////////////////////////////////
    }

    ////////// FUNCTION BLOCK //////////////////
    private void launchEXE(String path) throws IOException {
        File file = new File(path);
        if(file.exists()) {
            Desktop.getDesktop().open(file);
        }
    }
    private void fillSetupList() {
        ObservableList<ListElement> sList = FXCollections.observableArrayList();
        String[] sutEl;

        File file = new File(path);
        if(file.exists()){
            sList = PW.diskList(path);
            setupList.setItems(sList);
            setupList.setCellFactory(param -> new Cell());
            System.out.println("List files OK");
            logList.appendText("List files OK\n");
        }else{
            logList.appendText("List not exist\n");
            System.out.println("Not Exist");
        }

    }
    private void clearSetupList(){
        setupList.setItems(null);
        propView.clear();
        logList.appendText("List cleared.\n");
    }
    private void markDisk(){
        ArrayList<String> res = new ArrayList<>();
        ArrayList<String> template;

        String tmp = "";

        int i = 0;
        while (diskColumn.getCellData(i) != null){
            tmp = "";
            tmp += newName(diskColumn.getCellData(i));
            res.add(tmp);
            i++;
        }
        System.out.println(res);
        for (int j = 0; j < res.size(); j++){
            if ((checker[j] == null) || checker[j].toLowerCase().equals("do not mark")){
                System.out.println("do not mark");
                logList.appendText(res.get(j) + " -> do not mark\n");
            }
            else {
                if (checker[j].toLowerCase().equals("documents")){
                    template = templateMark.getDocTemplate();
                    PW.createPath(res.get(j), template, logList);
                    System.out.println("done mark");

                }
                if (checker[j].toLowerCase().equals("programs")){
                    template = templateMark.getProgTemplate();
                    PW.createPath(res.get(j), template, logList);
                    System.out.println("done mark");
                }
            }
        }
    }
    private static String newName(String name){

        String new_Name = "";
        char[] tmp = name.toCharArray();

        for(int i = name.length()-2; i < name.length(); i++){
            new_Name += tmp[i];
        }

        return new_Name;
    }

    ////////////////////////////////////////////

}