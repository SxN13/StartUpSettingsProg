package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.marnic.jiconextract.extractor.IconSize;
import me.marnic.jiconextract.extractor.JIconExtractor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javafx.scene.control.TextArea;

public class PW {

    public static ObservableList<ListElement> diskList(String path){

        File files = new File(path);
        ObservableList<ListElement> elementList = FXCollections.observableArrayList();
        BufferedImage icon;

        if(files.exists()){
            for (File file : files.listFiles()){

                icon = JIconExtractor.getJIconExtractor().extractIconFromFile(file.getAbsolutePath(), IconSize.SMALL);
                ListElement tmp = new ListElement(newFileName(file.getName()), file.getAbsolutePath(), toKb(file.length()), icon);
                elementList.add(tmp);

            }
        }else{
            System.out.println("Not Exist!");
        }
        return elementList;
    }

    //Имя файла без  расширения
    private static String newFileName(String name){

        String newName = "";
        char[] tmp = name.toCharArray();

        for(int i = 0; i < name.length()-4; i++){
            newName += tmp[i];
        }

        return newName;
    }

    //Перевод в мега и гига байты
	private static double toKb(long size){ return round(((double)size / (1024)),2); }

	//Округление 
	public static double round(double value, int places) {

        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;

    }

    public static ArrayList<DiskElement> findDisks(){
        ////////////////////Латинский алфавит
        int[] amap = new int[26]; 

        DiskElement diskElement;

        for (int i = 0; i <= 25; i++) {
            amap[i] = i + 97;
        }
		/////////////////////////////////////
        ArrayList<DiskElement> resDirList = new ArrayList<DiskElement>();
        String tmp;

        //Если диск такой буквы существует, то в массив
        for (int i = 0; i < amap.length; i++){
            if(!String.valueOf((char)amap[i]).toUpperCase().equals("C")) {
                tmp = String.valueOf((char) amap[i]).toUpperCase() + ":";
                File disc = new File(tmp);
                if (disc.exists()) {
                    diskElement = new DiskElement(tmp);
                    resDirList.add(diskElement);
                }
            }
        }
        return resDirList;
    }
    public static void createPath(String root, ArrayList<String> template, TextArea textArea) {

        File dir;
        boolean ok = false;

        for(String path : template){
            dir = new File(root + path);
            if(dir.mkdirs()) {
                ok = true;
                System.out.println("created -> " + root + path + " Status " + ok);
                textArea.appendText("created -> " + root + path + " Status " + ok + "\n");
            }
            else{
                System.out.println("not created -> " + root + path + " Status " + ok);
                textArea.appendText("not created -> " + root + path + " Status " + ok + "\n");
            }
        }
    }
}
