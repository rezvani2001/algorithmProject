package Translator.LOGIC.BST;


import Translator.LOGIC.WordNode;

public class MyBST {
    public BSTNode root;
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
            this.cost += root.insertNode(word);
        }
        size++;
    }

    public String search(String key){
        return this.root.search(key);
    }
}
