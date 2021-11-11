package Bag;

import java.util.Iterator;

public class ArrayBag<T> implements Bag<T>{

    private int num;
    private T[] elems;


    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void insert(T x) {

    }

    @Override
    public boolean isElem(T x) {
        return false;
    }

    @Override
    public void delete(T x) {

    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
