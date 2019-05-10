package sample;

import java.awt.image.BufferedImage;

import static java.lang.Double.NaN;

public class ListElement {
    private String name, path;
    double size;
    BufferedImage icon;

    public void setNULL(){
        name = null;
        path = null;
        size = NaN;
        icon = null;
    }

    public ListElement(String name, String path, double size, BufferedImage icon){
        this.name = name;
        this.path = path;
        this.size = size;
        this.icon = icon;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public BufferedImage getIcon() {
        return icon;
    }

    public void setIcon(BufferedImage icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
