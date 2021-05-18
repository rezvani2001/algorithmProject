package LOGIC.BST;

import LOGIC.WordNode;

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

    public double insertNode(WordNode word, int depth) {

        if (word.key.compareTo(this.key) > 0) {
            if (this.left == null) {
                this.left = new BSTNode(word, this.depth + 1);
                return this.left.worth();
            } else return left.insertNode(word, this.depth);
        } else if (word.key.compareTo(this.key) < 0) {
            if (this.right == null) {
                this.right = new BSTNode(word, this.depth + 1);
                return this.right.worth();
            } else return this.right.insertNode(word, this.depth);
        } else return 0.0;
    }

    public double worth() {
        return this.possibility * this.depth;
    }
}
