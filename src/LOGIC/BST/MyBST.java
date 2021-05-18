package LOGIC.BST;


import LOGIC.WordNode;

public class MyBST {
    BSTNode root;
    public int size;
    public double cost;

    public MyBST() {
        root = null;
        size = 0;
        cost = 0.0;
    }


    public void insert(WordNode word) {
        if (size == 0){
            this.root = new BSTNode(word , 1);
            this.cost += root.worth();
        } else {
            this.cost += root.insertNode(word , 1);
        }
        size++;
    }
}
