package Translator.LOGIC;

import Translator.LOGIC.BST.MyBST;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadTree {
    public static MyBST[] optimalBST;


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
            words[key.charAt(0) - 97].add(new WordNode(key , scanner.next() , scanner.nextDouble()));
        }

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < words[i].size(); j++) {
                optimalBST[i].insert(words[i].get(j));
            }
        }

        return System.currentTimeMillis() - time;
    }
}

