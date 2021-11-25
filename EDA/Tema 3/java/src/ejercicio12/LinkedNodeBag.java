package Bag;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedNodeBag<T extends Comparable<T>> implements Bag<T>{

    static private class Node<T> {
        T elem;
        int num;
        Node<T> next;

        public Node(T x, int y, Node<T> node) {
            elem=x;
            num=y;
            next= node;
        }

        @Override
        public String toString() {
            return "Node( "+elem+", "+num+", "+next+")";
        }

        public void aumentar(){
            num++;
        }

        public void disminuir(){
            num--;
        }
    }

    private Node<T> top;
    private int numElements;

    public LinkedNodeBag(){
        top=new Node<>(null, 0, null);
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
        if(top.elem==null || top.elem==x){
            top.elem=x;
            top.aumentar();
            if(top.num==1){
                numElements++;
            }
        }else{
            Node<T> aux = top;
            boolean aux2= false;
            while(aux.next!=null && !aux2){
                if(aux.elem==x){
                    aux.aumentar();
                    aux2= true;
                }else if(x.compareTo(aux.elem)<0){
                    aux.next = new Node<>(aux.elem, aux.num, aux.next);
                    aux.elem = x;
                    aux.num = 1;
                    numElements++;
                    aux2=true;
                }
                aux= aux.next;
            }
            if(!aux2 && x.compareTo(aux.elem)<0){
                aux.next = new Node<>(aux.elem, aux.num, aux.next);
                aux.elem = x;
                aux.num = 1;
                numElements++;
            }else if(!aux2){
                aux.next= new Node<>(x,1, null);
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
            Node<T> aux = top;
            boolean borrado = false;
            while(aux.next!=null && !borrado){
                if(aux.elem==x){
                    aux.disminuir();
                    borrado = true;
                }else{
                    aux= aux.next;
                }
            }
            if(!borrado && aux.elem==x){
                aux.disminuir();
                borrado = true;
            }
            if(borrado && aux.num==0){
                if(aux.next==null){
                    aux=null;
                }else{
                    aux.elem= aux.next.elem;
                    aux.num= aux.next.num;
                    aux.next= aux.next.next;
                }
                numElements--;
            }
        }
    }

    @Override
    public int occurrences(T x) {
        if(isElem(x)){
            Node aux = top;
            while(aux!=null){
                if(aux.elem==x){
                    return aux.num;
                }
                aux= aux.next;
            }
        }
        return 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedNodeBagIterator();
    }

    private class LinkedNodeBagIterator implements Iterator<T> {
        Node<T> current;
        int num;

       public LinkedNodeBagIterator(){
            current= top;
            num = top.num;
        }

        @Override
        public boolean hasNext() {
            return current!=null;
        }

        @Override
        public T next() {
           if(!hasNext()){
               throw new NoSuchElementException();
           }
           T x = current.elem;
           num--;
            if(num==0){
                current= current.next;
                if(current!=null){
                    num= current.num;
                }
            }
           return x;
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Node<T> aux = top;
        for(int i=0; i<numElements; i++){
            str.append(aux.elem).append(":").append(aux.num);
            aux= aux.next;
            if(i+1<numElements){
                str.append(", ");
            }
        }
        return "LinkedNodeBag("+str.toString()+")";
    }

    public static void main(String[] args) {
        LinkedNodeBag<Integer> a = new LinkedNodeBag<Integer>();
        a.insert(1);
        a.insert(2);
        a.insert(1);
        a.insert(4);
        a.insert(5);
        a.insert(3);
        a.insert(3);
        a.insert(4);
        a.delete(3);
        a.delete(3);
        System.out.println(a);
        Iterator<Integer> b = a.iterator();
        while(b.hasNext()){
            System.out.println(b.next());
        }
    }
}
