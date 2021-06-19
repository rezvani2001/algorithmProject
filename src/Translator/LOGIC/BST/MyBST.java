package Translator.LOGIC.BST;


import Translator.LOGIC.WordNode;

/**
 * the structure of optimal-BST
 */
public class MyBST {
    /**
     * it holds the root of the tree.
     */
    public BSTNode root;

    /**
     * it counts the elements that exist in the tree
     */
    public int size;

    /**
     * simple constructor.
     */
    public MyBST() {
        root = null;
        size = 0;
    }


    /**
     * this method gets a {@link WordNode wprd} and adds it to the tree
     * @param word
     */
    public void insert(WordNode word) {
        if (size == 0){
            this.root = new BSTNode(word , 1);
        } else {
           root.insertNode(word);
        }
        size++;
    }

    /**
     * it searches the given {@link String key} in the tree and returns the translation
     * @param key
     * @return the translation of the word.
     */
    public String search(String key){
        return this.root.search(key);
    }
}
