package LOGIC;

import LOGIC.BST.MyBST;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static int minJ;

    public static void main(String[] args) throws FileNotFoundException {
        long time = System.currentTimeMillis();

        Scanner scanner = new Scanner(new BufferedReader(new FileReader("dictionary.txt")));

        ArrayList<WordNode> words = new ArrayList<>(31806);

        while (scanner.hasNext()) {
            words.add(new WordNode(scanner.next(), scanner.next(), scanner.nextDouble()));
        }

        MyBST optimalBST = new MyBST();
        optimalBST.insert(words.get(57));

        for (WordNode word : words){
            optimalBST.insert(word);
        }

        System.out.println(System.currentTimeMillis() - time);
    }
}

