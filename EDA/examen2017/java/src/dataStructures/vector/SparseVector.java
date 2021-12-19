/******************************************************************************
 * Student's name:
 * Student's group:
 * Data Structures. Grado en Informática. UMA.
******************************************************************************/

package dataStructures.vector;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SparseVector<T> implements Iterable<T> {

    protected interface Tree<T> {

        T get(int sz, int i);

        Tree<T> set(int sz, int i, T x);
    }

    // Unif Implementation

    protected static class Unif<T> implements Tree<T> {

        private T elem;

        public Unif(T e) {
            elem = e;
        }

        @Override
        public T get(int sz, int i) {
            return elem;
        }

        @Override
        public Tree<T> set(int sz, int i, T x) {
            if(elem.equals(x)) return this;
            if(sz==1){
                return new Unif<T>(x);
            }else{
                Node<T> t;
                if(i<sz/2){
                    t = new Node<>(set(sz / 2, i, x), this);
                }else{
                    t = new Node<>(this, set(sz / 2, i - (sz / 2), x));
                }
                return t;
            }
        }

        @Override
        public String toString() {
            return "Unif(" + elem + ")";
        }
    }

    // Node Implementation

    protected static class Node<T> implements Tree<T> {

        private Tree<T> left, right;

        public Node(Tree<T> l, Tree<T> r) {
            left = l;
            right = r;
        }

        @Override
        public T get(int sz, int i) {
            if(i<sz/2){
                return left.get(sz/2, i);
            }else{
                return right.get(sz/2, i-sz/2);
            }
        }

        @Override
        public Tree<T> set(int sz, int i, T x) {
            if(i<sz/2){
                left = left.set(sz/2, i,x);
            }else{
                right = right.set(sz/2, i-sz/2, x);
            }
            simplify();
            return this;
        }

        protected Tree<T> simplify() {
            if(left instanceof Unif<?> && right instanceof Unif<?> && left.get(1,0)== right.get(1,0)){
                return (Unif<T>) left;
            }
            return this;
        }

        @Override
        public String toString() {
            return "Node(" + left + ", " + right + ")";
        }
    }

    // SparseVector Implementation

    private int size;
    private Tree<T> root;

    public SparseVector(int n, T elem) {
        if(n<0){
            throw new VectorException("N menor que 0");
        }
        size = (int) Math.pow(2, n);
        root = new Unif<T>(elem);

    }

    public int size() {
        return size;
    }

    public T get(int i) {
        if(i<0 || i+1>size){
            throw new VectorException("Indice erroneo");
        }
        return root.get(size, i);
    }

    public void set(int i, T x) {
        if(i<0 || i+1>size){
            throw new VectorException("Indice erroneo");
        }
        root = root.set(size, i, x);
    }

    @Override
    public Iterator<T> iterator() {
        return new SparceVectorIterator();
    }

    private class SparceVectorIterator implements Iterator<T>{

        int i;

        public SparceVectorIterator(){
            i=0;
        }

        @Override
        public boolean hasNext() {
            return i<size;
        }

        @Override
        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            T a = root.get(size, i);
            i++;
            return a;
        }
    }

    @Override
    public String toString() {
        return "SparseVector(" + size + "," + root + ")";
    }
}
