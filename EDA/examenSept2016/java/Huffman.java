
/**
 * Huffman trees and codes.
 *
 * Data Structures, Grado en Informatica. UMA.
 *
 *
 * Student's name:
 * Student's group:
 */

import dataStructures.dictionary.AVLDictionary;
import dataStructures.dictionary.Dictionary;
import dataStructures.list.LinkedList;
import dataStructures.list.List;
import dataStructures.priorityQueue.BinaryHeapPriorityQueue;
import dataStructures.priorityQueue.PriorityQueue;
import dataStructures.tuple.Tuple2;

public class Huffman {

    // Exercise 1
    public static Dictionary<Character, Integer> weights(String s) {
    	Dictionary<Character, Integer> aux = new AVLDictionary<>();
        for(int i=0; i<s.length(); i++){
            if(aux.isDefinedAt(s.charAt(i))){
                aux.insert(s.charAt(i), aux.valueOf(s.charAt(i))+1);
            }else{
                aux.insert(s.charAt(i), 1);
            }
        }
        return aux;
    }

    // Exercise 2.a
    public static PriorityQueue<WLeafTree<Character>> huffmanLeaves(String s) {
    	Dictionary<Character, Integer> aux = weights(s);
        PriorityQueue<WLeafTree<Character>> aux2 = new BinaryHeapPriorityQueue<>();
    	for(char i : aux.keys()){
            aux2.enqueue(new WLeafTree<Character>(i, aux.valueOf(i)));
        }
        return aux2;
    }

    // Exercise 2.b
    public static WLeafTree<Character> huffmanTree(String s) {
    	PriorityQueue<WLeafTree<Character>> aux = huffmanLeaves(s);
        while(!aux.isEmpty()){
            WLeafTree<Character> a = aux.first();
            aux.dequeue();
            WLeafTree<Character> b = aux.first();
            aux.dequeue();
            WLeafTree<Character> c = new WLeafTree<Character>(a, b);
            if(aux.isEmpty()){
                return c;
            }else{
                aux.enqueue(c);
            }
        }
        return aux.first();
    }

    // Exercise 3.a
    public static Dictionary<Character, List<Integer>> joinDics(Dictionary<Character, List<Integer>> d1, Dictionary<Character, List<Integer>> d2) {
        for(char i : d2.keys()){
            d1.insert(i, d2.valueOf(i));
        } 
    	return d1;
    }

    // Exercise 3.b
    public static Dictionary<Character, List<Integer>> prefixWith(int i, Dictionary<Character, List<Integer>> d) {
        for(char a : d.keys()){
            List<Integer> aux = d.valueOf(a);
            if(aux!=null){
                aux.prepend(i);
                d.insert(a, aux);
            }
        } 
    	return d;
    }

    // Exercise 3.c
    public static Dictionary<Character, List<Integer>> huffmanCode(WLeafTree<Character> ht) {
        if(ht.isLeaf()){
            Dictionary<Character, List<Integer>> aux = new AVLDictionary<>();
            aux.insert(ht.elem(), new LinkedList<Integer>());
            return aux;
        }else{
            return joinDics(prefixWith(0,huffmanCode(ht.leftChild())), prefixWith(1,huffmanCode(ht.rightChild())));
        }
    }

    // Exercise 4
    public static List<Integer> encode(String s, Dictionary<Character, List<Integer>> hc) {
        //to do 
    	return null;
    }

    // Exercise 5
    public static String decode(List<Integer> bits, WLeafTree<Character> ht) {
        //to do 
    	return null;
    }
}
