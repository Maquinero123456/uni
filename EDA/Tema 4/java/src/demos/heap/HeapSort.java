package demos.heap;

import java.util.Arrays;
import java.util.Random;

import dataStructures.heap.*;

public class HeapSort {

    public static int [] heapSort(int xs[], Heap<Integer> h) {

        // vaciamos el heap h
        while(! h.isEmpty()) {
            h.delMin();
        }

        int size = xs.length;
        for (int x : xs) h.insert(x);

        int ys[] = new int[size];
        for(int i=0; i<size; i++) {
            ys[i] = h.minElem();
            h.delMin();
        }
        return ys;
    }

    public static void print(int xs[]) {
        for (int x : xs) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    public static void randomHeap(int seed, int hsize, Heap<Integer> h) {
        Random rnd = new Random(seed);

        while(!h.isEmpty()) {
            h.delMin();
        }

        for(int i=0; i<hsize; i++)
            h.insert(rnd.nextInt());
    }

    public static boolean testHeapSort(int seed, int size, Heap<Integer> h) {
        Random rnd = new Random(seed);

        int xs[] = new int[size];
        for(int i=0; i<size; i++)
            xs[i] = rnd.nextInt();

        int ys[] = heapSort(xs,h);

        if(!h.isEmpty())
            return false;
        Arrays.sort(xs);
        return Arrays.equals(xs, ys);
    }

    static void tests(Heap<Integer> h,  String implementation) {
        final int NUM_TESTS = 1000;
        final int LENGTH_ARRAY = 1000;

        long t0 = System.currentTimeMillis();
        for(int seed=0; seed<NUM_TESTS; seed++)
            if(!testHeapSort(seed,LENGTH_ARRAY,h)) {
                System.out.println("Error on test");
                System.exit(0);
            }
        System.out.println("Use "+implementation);
        System.out.println("-> "+NUM_TESTS+" tests passed OK");
        long t1 = System.currentTimeMillis();
        System.out.printf("-> Took %f seconds\n", (t1 - t0) / 1e3 / NUM_TESTS);
    }

    public static void main(String[] args) {
        tests(new BinaryHeap<>(), "BinaryHeap<Integer>" );
        tests(new WBLeftistHeap<>(), "WeigthBiasedLeftistHeap<Integer>");
        tests(new MaxiphobicHeap<>(),"MaxiphobicHeap<Integer>");
        tests(new SkewHeap<>(),"SkewHeap<Integer>");
        tests(new PairingHeap<>(),"PairingHeap<Integer>");
    }

}