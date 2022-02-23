/**
 * Estructuras de Datos. Grado en Informática, IS e IC. UMA.
 * Examen de Febrero 2015.
 *
 * Implementación del TAD Deque
 *
 * Apellidos:
 * Nombre:
 * Grado en Ingeniería ...
 * Grupo:
 * Número de PC:
 */

package dataStructures.doubleEndedQueue;

public class LinkedDoubleEndedQueue<T> implements DoubleEndedQueue<T> {

    private static class Node<E> {
        private E elem;
        private Node<E> next;
        private Node<E> prev;

        public Node(E x, Node<E> nxt, Node<E> prv) {
            elem = x;
            next = nxt;
            prev = prv;
        }
    }

    private Node<T> first, last;

    /**
     *  Invariants:
     *  if queue is empty then both first and last are null
     *  if queue is non-empty:
     *      * first is a reference to first node and last is ref to last node
     *      * first.prev is null
     *      * last.next is null
     *      * rest of nodes are doubly linked
     */

    /**
     * Complexity:
     */
    public LinkedDoubleEndedQueue() {
        first = null;
        last = null;
    }

    /**
     * Complexity:
     */
    @Override
    public boolean isEmpty() {
        return first == null && last == null;
    }

    /**
     * Complexity:
     */
    @Override
    public void addFirst(T x) {
        if(isEmpty()){
            Node<T> aux = new Node<>(x, last, first);
            first = new Node<>(null, aux, null);
            last = new Node<>(null, null, aux);
        }else{
            Node<T> aux = first.next;
            Node<T> aux2 = new Node<>(x, aux, first);
            first = new Node<>(null, aux2, null);
        }
    }

    /**
     * Complexity:
     */
    @Override
    public void addLast(T x) {
        if(isEmpty()){
            Node<T> aux = new Node<>(x, last, first);
            first = new Node<>(null, aux, null);
            last = new Node<>(null, null, aux);
        }else{
            Node<T> aux = last.next;
            Node<T> aux2 = new Node<>(x, last, aux);
            last = new Node<>(null, null, aux2);
        }
    }

    /**
     * Complexity:
     */
    @Override
    public T first() {
        if(isEmpty()){
            throw new EmptyDoubleEndedQueueException("Vacia");
        }
        return first.next.elem;
    }

    /**
     * Complexity:
     */
    @Override
    public T last() {
        if(isEmpty()){
            throw new EmptyDoubleEndedQueueException("Vacia");
        }
        return last.prev.elem;
    }

    /**
     * Complexity:
     */
    @Override
    public void deleteFirst() {
        if(isEmpty()){
            throw new EmptyDoubleEndedQueueException("Vacia");
        }
        first = new Node<>(null, first.next.next, null);
        if(first.next==null){
            first = null;
            last = null;
        }
    }

    /**
     * Complexity:
     */
    @Override
    public void deleteLast() {
        if(isEmpty()){
            throw new EmptyDoubleEndedQueueException("Vacia");
        }
        last = new Node<>(null, null, last.prev.prev);
        if(last.prev==null){
            first = null;
            last = null;
        }
    }

    /**
     * Returns representation of queue as a String.
     */
    @Override
    public String toString() {
    String className = getClass().getName().substring(getClass().getPackage().getName().length()+1);
        String s = className+"(";
        for (Node<T> node = first; node != null; node = node.next)
            s += node.elem + (node.next != null ? "," : "");
        s += ")";
        return s;
    }
}
