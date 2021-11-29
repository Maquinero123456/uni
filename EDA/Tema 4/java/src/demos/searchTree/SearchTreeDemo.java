package demos.searchTree;

import dataStructures.searchTree.BST;
import dataStructures.searchTree.SearchTree;

public class SearchTreeDemo {

    public static void main(String args[]) {
        SearchTree<Integer,Integer> t = new BST<>();

        int xs[] = new int[] { 8,5,7,1,9,6 };

        for(Integer x : xs)
            t.insert(x, 10*x);

        System.out.println("Minim " + t.minim());
        System.out.println("Maxim " + t.maxim());

        t.deleteMinim();

        System.out.println("Minim " + t.minim());
        System.out.println("Maxim " + t.maxim());

        t.deleteMinim();

        System.out.println("Minim " + t.minim());
        System.out.println("Maxim " + t.maxim());

        t.deleteMaxim();

        System.out.println("Minim " + t.minim());
        System.out.println("Maxim " + t.maxim());

        t.deleteMinim();

        System.out.println("Minim " + t.minim());
        System.out.println("Maxim " + t.maxim());

        t.deleteMaxim();

        System.out.println("Minim " + t.minim());
        System.out.println("Maxim " + t.maxim());

    }
}
