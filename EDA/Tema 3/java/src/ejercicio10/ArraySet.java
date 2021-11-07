package ejercicio10;
import dataStructures.set.Set;
import java.util.Arrays;
import java.util.Iterator;

public class ArraySet<T extends Comparable<T>> implements Set<T>{

    protected T[] elements;
    protected int numberElements;

    private static final int DEFAULT_INITIAL_CAPACITY = 128;
    //Con tamaño n
    public ArraySet(int n){
        elements = (T[]) new Comparable[n];
        numberElements=0;
    }
    //Con tamaño default
    public ArraySet(){
        this(DEFAULT_INITIAL_CAPACITY);
    }

    private void ensureCapacity() {
        if (numberElements == elements.length) {
            elements = Arrays.copyOf(elements, 2*elements.length);
        }
    }

    @Override
    public boolean isEmpty() {
        return numberElements==0;
    }

    @Override
    public int size() {
        return numberElements;
    }

    @Override
    public void insert(T x) {
        T aux;
        if(!isElem(x)){
            ensureCapacity();
            for(int i=0; i<=numberElements;i++){
                if(isEmpty()){
                    elements[0]=x;
                } else if(elements[i]==null || x.compareTo(elements[i])<0){
                    aux= elements[i];
                    elements[i]=x;
                    x=aux;
                }
            }
            numberElements++;
        }
    }

    @Override
    public boolean isElem(T x) {
        for(int i=0; i<numberElements;i++){
            if(elements[i]==x){
                return true;
            }
        }
        return false;
    }

    @Override
    public void delete(T x) {
        if(isElem(x) && !isEmpty()){
            for(int i=0; i<numberElements;i++){
                if(x.compareTo(elements[i])<=0){
                    elements[i]=elements[i+1];
                }
            }
            elements[numberElements-1]=elements[numberElements];
            numberElements--;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(int i=0; i<numberElements; i++){
            str.append(elements[i]);
            if(i+1<numberElements){
                str.append(", ");
            }
        }
        return "ArraySet("+ str.toString() +")";
    }
}
