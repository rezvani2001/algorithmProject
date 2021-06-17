package Translator.GUI;

import Translator.LOGIC.LoadTree;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * the Stage in which the {@link Translator.LOGIC.WordNode WordNodes} of the translation tree are shown
 */

public class TreeStage extends Stage {
    public TreeStage() {
        BorderPane pane = new BorderPane();
        pane.setId("MainTreeStagePane");

        VBox buttonBox = new VBox(10);
        buttonBox.setId("TreeStageButtons");
        buttonBox.setPadding(new Insets(20));
        buttonBox.setAlignment(Pos.CENTER);
        ScrollPane buttonPane = new ScrollPane(buttonBox);
        buttonPane.setId("ButtonsScrollPane");
        buttonPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        ScrollPane treePane = new ScrollPane();
        treePane.setId("TreePane");
        treePane.setPadding(new Insets(20));

        for (int i = 0; i < 26 ; i++) {
            Button button = new Button("" + (char) (i + 97));
            button.setAccessibleHelp("" + i);
            button.setPrefSize(50 , 30);

            button.setOnAction(event -> {
                VBox treeBox = new VBox(10);
                treeBox.setId("TreeBox");
                treePane.setContent(treeBox);
                treeBox.getChildren().addAll(
                        LoadTree.optimalBST[Integer.parseInt(button.getAccessibleHelp())].root.getGUINode());
            });

            buttonBox.getChildren().addAll(button);
        }

        pane.setCenter(treePane);
        pane.setLeft(buttonPane);
        this.setTitle("Word Tree Nodes");
        Scene treeStageScene = new Scene(pane);
        treeStageScene.getStylesheets().add("Translator/GUI/CssFiles/TreeStageCssFile.css");
        this.setScene(treeStageScene);
        this.setWidth(1250);
        this.setHeight(800);
        this.setResizable(false);
    }
}
