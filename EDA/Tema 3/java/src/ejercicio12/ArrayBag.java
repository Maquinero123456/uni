package Bag;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayBag<T extends Comparable<T>> implements Bag<T>{

    private Tuple2<T, Integer>[] elems;
    private int numElem;

    private static final int DEFAULT_INITIAL_CAPACITY = 128;

    public ArrayBag(){
        elems = new Tuple2[DEFAULT_INITIAL_CAPACITY];
        numElem=0;
    }

    public ArrayBag(int n){
        elems = new Tuple2[n];
        numElem=0;
    }

    public void ensureCapacity(){
        if(numElem+1>elems.length){
            elems= Arrays.copyOf(elems, numElem+1);
        }
    }

    @Override
    public boolean isEmpty() {
        return numElem==0;
    }

    @Override
    public int size() {
        return numElem;
    }

    @Override
    public void insert(T x) {
        if(isEmpty()){
            elems[0] = new Tuple2<T, Integer>(x,1);
            numElem++;
        }else{
            boolean aumentado=false;
            int aux2=1;
            for(int i =0; i<numElem && !aumentado;i++){
                if(elems[i]._1()==x){
                    elems[i] = new Tuple2<>(elems[i]._1(), elems[i]._2()+1);
                    aumentado= true;
                }else if(x.compareTo(elems[i]._1())<0){
                    T aux= elems[i]._1();
                    int aux3 = elems[i]._2();
                    elems[i]= new Tuple2<>(x, aux2);
                    x=aux;
                    aux2=aux3;
                }
            }

            if(!aumentado){
                ensureCapacity();
                elems[numElem]= new Tuple2<>(x, aux2);
                numElem++;
            }
        }
    }

    @Override
    public boolean isElem(T x) {
        for(int i=0; i<numElem && !isEmpty(); i++){
            if(elems[i]._1()==x){
                return true;
            }else if(x.compareTo(elems[i]._1())<0){
                return false;
            }
        }
        return false;
    }

    @Override
    public void delete(T x) {
        if(!isEmpty() && isElem(x)){
            boolean cambiar= false;
            for(int i= 0; i<numElem; i++){
                if(elems[i]._1()==x && elems[i]._2()-1==0){
                    cambiar= true;
                    numElem--;
                }else if(elems[i]._1()==x){
                    elems[i] = new Tuple2<>(x, elems[i]._2()-1);
                }

                if(cambiar){
                    elems[i]=elems[i+1];
                }
            }

        }
    }

    @Override
    public int occurrences(T x) {
        if(isElem(x)){
            for(int i = 0; i<numElem; i++){
                if(elems[i]._1()==x){
                    return elems[i]._2();
                }
            }
        }
        return 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayBagIterator();
    }

    private class ArrayBagIterator implements Iterator<T> {
        Tuple2<T, Integer>[] current;
        int num;
        int pos;

        public ArrayBagIterator(){
            if(elems.length==0){
                throw new NoSuchElementException();
            }
            current= elems;
            num = elems[0]._2();
            pos= 0;
        }

        @Override
        public boolean hasNext() {
            return current[pos]!=null || pos>current.length-1;
        }

        @Override
        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            T x = current[pos]._1();
            num--;
            if(num==0){
                pos++;
                if(current[pos]!=null && pos<current.length-1){
                    num= current[pos]._2();
                }
            }
            return x;
        }
    }

    @Override
    public String toString(){
        StringBuilder st= new StringBuilder();
        for(int i =0; i<numElem; i++){
            st.append(elems[i]._1()+":"+elems[i]._2());
            if(i!=numElem-1){
                st.append(", ");
            }
        }
        return "ArrayBag("+st.toString()+")";
    }

    public static void main(String[] args) {
        ArrayBag<Character> a = new ArrayBag<Character>(1);
        int aux = 65;
        for(int i = 1; i<59; i++){
            a.insert((char)aux);
            aux++;
        }
        a.insert(',');
        System.out.println(a);
    }
}
