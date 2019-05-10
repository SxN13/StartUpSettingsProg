package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cell extends ListCell<ListElement> {

    private ImageView img = new ImageView();

    @Override
    protected void updateItem(ListElement item, boolean empty) {
        super.updateItem(item, empty);

        if (item != null && !empty){
            Image icon = SwingFXUtils.toFXImage(item.getIcon(), null);
            img.setFitHeight(15);
            img.setFitWidth(15);
            img.setImage(icon);
            setGraphic(img);
            setText(item.getName());
        }
        else{
            setText(null);
            setGraphic(null);
        }

    }
}