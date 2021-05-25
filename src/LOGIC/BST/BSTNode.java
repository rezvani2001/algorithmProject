package LOGIC.BST;

import LOGIC.WordNode;
import com.sun.org.apache.xpath.internal.objects.XNull;
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


    private AnchorPane GUIPane;

    public BSTNode(WordNode word, int depth) {
        this.possibility = word.possibility;
        this.key = word.key;
        this.value = word.value;
        this.depth = depth;
        this.left = this.right = null;
        this.GUIPane = null;
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


    public AnchorPane getGUINode() {
        AtomicBoolean leftTrigger = new AtomicBoolean(false);
        AtomicBoolean rightTrigger = new AtomicBoolean(false);

        if (this.GUIPane != null) {
            return this.GUIPane;
        } else {
            AnchorPane pane = new AnchorPane();

            VBox box = new VBox(10);
            VBox leftBox = new VBox(10);

            if (this.left != null){
                Button leftNode = new Button("key: " + this.left.key + "\nmeaning: " + this.left.value);

                leftNode.setOnAction(event -> {
                    if (leftTrigger.get()) {
                        leftBox.getChildren().remove(this.left.getGUINode());
                        leftTrigger.set(false);
                    } else {
                        leftBox.getChildren().addAll(this.left.getGUINode());
                        leftTrigger.set(true);
                    }
                });

                leftBox.getChildren().addAll(leftNode);

            }
            VBox rightBox = new VBox(10);

            if (this.right != null){
                Button rightNode = new Button("key: " + this.right.key + "\nmeaning: " + this.right.value);

                rightNode.setOnAction(event -> {
                    if (this.right != null) {
                        if (rightTrigger.get()) {
                            rightBox.getChildren().remove(this.right.getGUINode());
                            rightTrigger.set(false);
                        } else {
                            rightBox.getChildren().addAll(this.right.getGUINode());
                            rightTrigger.set(true);
                        }
                    }
                });

                rightBox.getChildren().addAll(rightNode);

            }
            box.getChildren().addAll(leftBox, rightBox);
            pane.getChildren().addAll(box);

            AnchorPane.setLeftAnchor(box, 30.0);

            this.GUIPane = pane;
            return pane;
        }
    }
}
