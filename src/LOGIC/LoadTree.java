package LOGIC;

import LOGIC.BST.MyBST;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadTree {
    public static MyBST optimalBST;


    public static long loadTree() throws FileNotFoundException {
        long time = System.currentTimeMillis();

        Scanner scanner = new Scanner(new BufferedReader(new FileReader("dictionary.txt")));

        ArrayList<WordNode> words = new ArrayList<>(31850);

        while (scanner.hasNext()) {
            words.add(new WordNode(scanner.next(), scanner.next(), scanner.nextDouble()));
        }

        MyBST optimalBST = new MyBST();
        optimalBST.insert(words.get(57));

        for (WordNode word : words){
            optimalBST.insert(word);
        }

        time = System.currentTimeMillis() - time;
        System.out.println(time);

        LoadTree.optimalBST = optimalBST;
        return time;
    }
}

