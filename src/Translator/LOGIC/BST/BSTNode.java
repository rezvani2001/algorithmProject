package Translator.LOGIC.BST;

import Translator.LOGIC.WordNode;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * this class represents the structure of BST nodes.
 */
public class BSTNode {
    /**
     * the possibility of the related {@link WordNode word}
     */
    public double possibility;

    /**
     * the key( english form) of the related {@link WordNode word}
     */
    public String key;

    /**
     * the translation of the related {@link WordNode word}
     */
    public String value;

    /**
     * the depth of the node in {@link MyBST tree}
     */
    public int depth;

    /**
     * the node which its key is bigger than the key of this object( the left node)
     */
    public BSTNode left;

    /**
     * the node which its key is smaller than the key of this object( the right node)
     */
    public BSTNode right;


    /**
     * the constructor of the word.
     *
     * gets a {@link WordNode word} and the depth from top of the {@link MyBST tree} and makes the asked node.
     *
     * @param word
     * @param depth
     */
    public BSTNode(WordNode word, int depth) {
        this.possibility = word.possibility;
        this.key = word.key;
        this.value = word.value;
        this.depth = depth;
        this.left = this.right = null;
    }


    /**
     * this method sets the given {@link WordNode word} in the right place in the {@link MyBST tree}
     * by passing it all the way down till a empty space founded.
     *
     * @param word
     */
    public void insertNode(WordNode word) {

        if (word.key.compareTo(this.key) > 0) {
            if (this.left == null) {
                this.left = new BSTNode(word, this.depth + 1);
            } else this.left.insertNode(word);
        } else if (word.key.compareTo(this.key) < 0) {
            if (this.right == null) {
                this.right = new BSTNode(word, this.depth + 1);
            } else this.right.insertNode(word);
        }
    }


    /**
     * this method makes and returns the GUI of this node to be shown in tree view.
     * when client clicks on GUI of this node, the GUI of left node will be appeared on top of it
     * and the GUI of right node will be appeared bellow it.
     *
     * @return {@link VBox} to be shown in tree view.
     */
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

                    AnchorPane.setLeftAnchor(child, 100.0);

                    leftBox.getChildren().add(anchorPane);
                }

                if (this.right != null) {
                    AnchorPane anchorPane = new AnchorPane();
                    VBox child = this.right.getGUINode();
                    anchorPane.getChildren().addAll(child);

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


    /**
     * this method looks for the given {@link String key} in the tree.
     * if the key is equal to the key of this object, it returns the translation.
     * if not, if the key is bigger than the object's key, it tells to the left node to look for the key,
     * else, it tells to the right one to do the job.
     *
     * @param key
     * @return translation of the given key
     */
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
