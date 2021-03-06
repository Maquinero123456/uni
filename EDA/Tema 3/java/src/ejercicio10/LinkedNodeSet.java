package ejercicio10;

import dataStructures.set.Set;
import ejercicio12.LinkedNodeBag;

import java.util.Iterator;

public class LinkedNodeSet<T extends Comparable<T>> implements Set<T> {

    static private class Node<T> {
        T elem;
        Node<T> next;

        public Node(T x, Node<T> node) {
            elem=x;
            next= node;
        }

        @Override
        public String toString() {
            return "Node( "+elem+", "+next+")";
        }
    }

    private Node<T> top;
    private int numElements;

    public LinkedNodeSet(){
        top=new Node<>(null, null);
        numElements=0;
    }

    @Override
    public boolean isEmpty() {
        return top.elem==null;
    }

    @Override
    public int size() {
        return numElements;
    }

    @Override
    public void insert(T x) {
        if(isEmpty()){
            top.elem=x;
            numElements++;
        }else if(!isElem(x)){
            Node<T> aux = top;
            boolean aux2= false;
            while(aux.next!=null && !aux2){
                if(x.compareTo(aux.elem)<0){
                    aux.next = new Node<>(aux.elem, aux.next);
                    aux.elem = x;
                    numElements++;
                    aux2=true;
                }
                aux= aux.next;
            }
            if(!aux2 && x.compareTo(aux.elem)<0){
                aux.next = new Node<>(aux.elem, aux.next);
                aux.elem = x;
                numElements++;
            }else if(!aux2){
                aux.next= new Node<>(x, null);
                numElements++;
            }
        }
    }

    @Override
    public boolean isElem(T x) {
        Node<T> aux= top;
        while(aux!=null){
            if(aux.elem==x){
                return true;
            }
            aux=aux.next;
        }
        return false;
    }

    @Override
    public void delete(T x) {
        if(!isEmpty() && isElem(x)){
            Node<T> aux= top;
            boolean borrado = false;
            while(aux.next!=null && !borrado){
                if(x.compareTo(aux.elem)==0){
                    aux.elem= aux.next.elem;
                    aux.next= aux.next.next;
                    borrado = true;
                }
                aux= aux.next;
            }
            if(aux.next==null){
                aux.elem=null;
            }
            numElements--;
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Node<T> aux = top;
        for(int i=0; i<numElements; i++){
            str.append(aux.elem);
            aux= aux.next;
            if(i+1<numElements){
                str.append(", ");
            }

        }
        return "LinkedNodeSet("+ str.toString() +")";
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    public static void main(String[] args) {
        LinkedNodeSet<Integer> a = new LinkedNodeSet<>();
        a.insert(1);
        a.insert(2);
        a.insert(4);
        a.insert(3);
        System.out.println(a);
        a.delete(4);
        System.out.println(a);
    }
}
