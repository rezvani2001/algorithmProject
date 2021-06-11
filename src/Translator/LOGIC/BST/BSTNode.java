package Translator.LOGIC.BST;

import Translator.LOGIC.WordNode;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.concurrent.atomic.AtomicBoolean;


public class BSTNode {
    public double possibility;
    public String key;
    public String value;
    public int depth;

    public BSTNode left;
    public BSTNode right;


    public BSTNode(WordNode word, int depth) {
        this.possibility = word.possibility;
        this.key = word.key;
        this.value = word.value;
        this.depth = depth;
        this.left = this.right = null;
    }

    public double insertNode(WordNode word) {

        if (word.key.compareTo(this.key) > 0) {
            if (this.left == null) {
                this.left = new BSTNode(word, this.depth + 1);
                return this.left.worth();
            } else return left.insertNode(word);
        } else if (word.key.compareTo(this.key) < 0) {
            if (this.right == null) {
                this.right = new BSTNode(word, this.depth + 1);
                return this.right.worth();
            } else return this.right.insertNode(word);
        } else return 0.0;
    }

    public double worth() {
        return this.possibility * this.depth;
    }

    public VBox getGUINode() {
        VBox pane = new VBox(10);
        pane.setId("MainTreePane");

        VBox leftBox = new VBox(10);

        Button button = new Button("key: " + this.key + "\nmeaning: " + this.value);
        button.setId("TreeNodeButton");
        button.setPrefSize(150, 55);

        VBox rightBox = new VBox(10);

        AtomicBoolean trigger = new AtomicBoolean(false);

        button.setOnAction(event -> {
            if (!trigger.get()) {
                if (this.left != null) {
                    AnchorPane anchorPane = new AnchorPane();
                    VBox child = this.left.getGUINode();
                    anchorPane.getChildren().addAll(child);
//                    anchorPane.setStyle("-fx-border-width: 2; -fx-border-color: black");

                    AnchorPane.setLeftAnchor(child, 100.0);

                    leftBox.getChildren().add(anchorPane);
                }

                if (this.right != null) {
                    AnchorPane anchorPane = new AnchorPane();
                    VBox child = this.right.getGUINode();
                    anchorPane.getChildren().addAll(child);
//                    anchorPane.setStyle("-fx-border-width: 2; -fx-border-color: black");

                    AnchorPane.setLeftAnchor(child, 100.0);

                    rightBox.getChildren().addAll(anchorPane);
                }
                trigger.set(true);
            } else {
                if (leftBox.getChildren().size() > 0)
                    leftBox.getChildren().remove(0);
                if (rightBox.getChildren().size() > 0)
                    rightBox.getChildren().remove(0);
                trigger.set(false);
            }
        });

        pane.getChildren().addAll(leftBox, button, rightBox);
        AnchorPane.setLeftAnchor(leftBox, 20.0);
        AnchorPane.setLeftAnchor(rightBox, 20.0);
        return pane;
    }



    public String search(String key){
        if (this.key.equals(key)){
            return this.value;
        } else if (this.key.compareTo(key) < 0){
            if (this.left == null) return "?";
            else return this.left.search(key);
        } else {
            if (this.right == null) return "?";
            else return this.right.search(key);
        }
    }
}
