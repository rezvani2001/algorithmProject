package Translator.LOGIC;

import Translator.LOGIC.BST.MyBST;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadTree {
    public static MyBST[] optimalBST;
    public static long buildTime;

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

    public static String search(String key) {
        if (key.charAt(0) < 97 || key.charAt(0) > 122) return "?";
        return optimalBST[key.charAt(0) - 97].search(key);
    }
}

