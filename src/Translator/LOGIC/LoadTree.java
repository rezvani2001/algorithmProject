package Translator.LOGIC;

import Translator.LOGIC.BST.MyBST;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * the class that makes and stores the optimal-BST of the given words.
 */
public class LoadTree {
    /**
     * the static field that stores the bst.
     *
     * it is static because in all of the project we have only one optimal-BST.
     */
    public static MyBST[] optimalBST;

    /**
     * holds the time that toke to make the optimal bst.
     */
    public static long buildTime;


    /**
     * this method reads all the words from the given file,
     * then it generates a optimal-BST for all the words that starts with the same letter
     * at the end there will be 26 different BSTs.
     *
     * @return
     * @throws FileNotFoundException
     */
    public static long loadTree() throws FileNotFoundException {
        long time = System.currentTimeMillis();

        Scanner scanner = new Scanner(new BufferedReader(new FileReader("dictionary.txt")));

        optimalBST = new MyBST[26];

        ArrayList<WordNode>[] words = new ArrayList[26];

        for (int i = 0; i < 26; i++) {
            optimalBST[i] = new MyBST();
            words[i] = new ArrayList<>();
        }

        while (scanner.hasNext()) {
            String key = scanner.next();
            words[key.charAt(0) - 97].add(new WordNode(key, scanner.next(), scanner.nextDouble()));
        }

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < words[i].size(); j++) {
                optimalBST[i].insert(words[i].get(j));
            }
        }

        buildTime = System.currentTimeMillis() - time;
        return buildTime;
    }


    /**
     * this method searches the given key in the BST that contains words with the same starting letter
     * and returns the translation of it, if there is no match, it returns "?"
     *
     * @param key
     * @return
     */
    public static String search(String key) {
        if (key.charAt(0) < 97 || key.charAt(0) > 122) return "?";
        return optimalBST[key.charAt(0) - 97].search(key);
    }
}

