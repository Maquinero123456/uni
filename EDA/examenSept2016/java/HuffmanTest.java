import dataStructures.dictionary.AVLDictionary;
import dataStructures.dictionary.Dictionary;
import dataStructures.list.LinkedList;
import dataStructures.list.List;

public class HuffmanTest {

    @SuppressWarnings("unused")
    public static void main(String[] args) {

        String magia = "abracadabra";
        String cabra = "abracadabra pata de cabra";
        String conjuro = "abracadabra pata de cabra si no sana hoy sanara mañana";
        String grecas = "te estoy amando locamente pero no se como te lo voy a decir "
                + "quisiera que me comprendieras y sin darte cuenta te alejas de mi";
        String humpty = "Humpty Dumpty sat on a wall, Humpty Dumpty had a great fall "
                + "all the king's horses and all the king's men couldn't put Humpty together again";
        String mustFail = "aaa";

        // choose the message to test your code

        String msg = magia;

        // your output should match that of 'magia.txt', 'cabra.txt', etc.

        System.out.println("tests for: " + msg);

        // Exercise 1

        System.out.println("\nExercise 1 - weights:");
        System.out.println(Huffman.weights(msg));

        // Exercise 2

        System.out.println("\nExercise 2.a - leaves:");
        System.out.println(Huffman.huffmanLeaves(msg));
        System.out.println("\nExercise 2.b - huffman tree with one priority queue:");
        System.out.print(Huffman.huffmanTree(msg));

        // Exercise 3

        System.out.println("\nExercise 3.a - join dictionaries:");
        Dictionary<Character, List<Integer>> d1 = new AVLDictionary<>();
        List<Integer> d1xs = new LinkedList<>();
        d1xs.append(1);
        d1xs.append(2);
        List<Integer> d1ys = new LinkedList<>();
        d1ys.append(3);
        d1ys.append(4);
        d1.insert('a', d1xs);
        d1.insert('c', d1ys);
        System.out.println("d1: " + d1);

        Dictionary<Character, List<Integer>> d2 = new AVLDictionary<>();
        List<Integer> d2xs = new LinkedList<>();
        d2xs.append(5);
        d2xs.append(6);
        List<Integer> d2ys = new LinkedList<>();
        d2ys.append(7);
        d2ys.append(8);
        d2.insert('b', d2xs);
        d2.insert('d', d2ys);
        System.out.println("d2: " + d2);
        System.out.println("join(d1,d2): " + Huffman.joinDics(d1, d2));

        System.out.println("\nExercise 3.b - prefix with:");
        System.out.println("prefix d1 with 0: " + Huffman.prefixWith(0, d1));
        System.out.println("prefix d2 with 1: " + Huffman.prefixWith(1, d2));

        // Exercise 4

        System.out.println("\nExercise 4 - encode:");

        System.out.println("using the huffman tree built with one priority queue:");
        WLeafTree<Character> ht = Huffman.huffmanTree(msg);
        Dictionary<Character, List<Integer>> hc = Huffman.huffmanCode(ht);
        List<Integer> codedMsg = Huffman.encode(msg, hc);
        System.out.println(codedMsg);


        // Exercise 5

        System.out.println("\nExercise 5 - decode:");

        System.out.println("using the huffman tree built with one priority queue:");
        String decodedMsg = Huffman.decode(codedMsg, ht);
        System.out.println(decodedMsg);
        if (msg.equals(decodedMsg)) {
            System.out.println("decoding OK!");
        } else {
            System.out.println("***** decoding WRONG!");
        }
    }
}
