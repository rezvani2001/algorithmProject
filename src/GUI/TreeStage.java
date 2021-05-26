package GUI;

import LOGIC.LoadTree;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class TreeStage extends Stage {
    public TreeStage() {
        ScrollPane scrollPane = new ScrollPane();

        VBox box = new VBox(10);


        box.getChildren().addAll(LoadTree.optimalBST.root.getGUINode());
        scrollPane.setContent(box);
        this.setScene(new Scene(scrollPane, 700, 700));
    }
}
