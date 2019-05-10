package sample;

public class DiskElement {
    private String diskName;

    public String getDiskName() {
        return diskName;
    }

    public DiskElement(String diskName){
        this.diskName = "Disk: "+ diskName;
    }

    @Override
    public String toString() {
        return "Disk: "+ diskName;
    }
}
