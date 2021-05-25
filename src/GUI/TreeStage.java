package GUI;

import LOGIC.LoadTree;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicBoolean;


public class TreeStage extends Stage {
    public TreeStage() {
        ScrollPane scrollPane = new ScrollPane();

        VBox box = new VBox(10);

        AtomicBoolean trigger = new AtomicBoolean(false);

        Button button = new Button("key: " + LoadTree.optimalBST.root.key + "\nmeaning: " +
                LoadTree.optimalBST.root.value);

        button.setOnAction(event -> {
            if (trigger.get()){
                box.getChildren().remove(LoadTree.optimalBST.root.getGUINode());
                trigger.set(false);
            } else {
                box.getChildren().addAll(LoadTree.optimalBST.root.getGUINode());
                trigger.set(true);
            }
        });

        box.getChildren().addAll(button);
        scrollPane.setContent(box);

        this.setScene(new Scene(scrollPane, 700, 700));
    }
}
