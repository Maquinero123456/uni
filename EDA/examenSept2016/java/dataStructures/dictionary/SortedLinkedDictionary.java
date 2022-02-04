/**
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 *
 * Dictionaries implemented using a sorted linked list
 */

package dataStructures.dictionary;

import dataStructures.tuple.Tuple2;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedLinkedDictionary<K extends Comparable<? super K>,V> implements Dictionary<K,V> {

    static private class Node<A,B> {
        A key;
        B value;
        Node<A,B> next;

        Node(A k, B v, Node<A,B> node) {
            key = k;
            value = v;
            next = node;
        }
    }

    protected Node<K,V> first;
    protected int size;

    public SortedLinkedDictionary() {
        first = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return first==null;
    }

    @Override
    public int size() {
        return size;
    }

    // Uses Pablo's approach to avoid code repetition
    private class Finder {
        Node<K,V> previous;
        Node<K,V> current;
        boolean found;

        public Finder(K k) {
            previous = null;
            current = first;
            while (current != null && current.key.compareTo(k) < 0) {
                previous = current;
                current = current.next;
            }
            found = (current != null) && current.key.equals(k);
        }
    }

    @Override
    public void insert(K k, V v) {
        Finder finder = new Finder(k);

        if (finder.found) {
            finder.current.value = v;
        } else {
            if (finder.previous == null) // Insert in first position
                first = new Node<>(k,v, first);
            else  // Insert after previous
                finder.previous.next = new Node<>(k, v, finder.current);
            size++;
        }
    }

    @Override
    public V valueOf(K k) {
        Finder finder = new Finder(k);

        return finder.found ? finder.current.value : null;
    }

    @Override
    public boolean isDefinedAt(K k) {
        Finder finder = new Finder(k);

        return finder.found;
    }

    @Override
    public void delete(K k) {
        Finder finder = new Finder(k);

        if(finder.found) {
            if (finder.previous == null) {
                first = first.next;
            } else {
                finder.previous.next = finder.current.next;
            }
            size--;
        }
    }

    @Override
    public String toString() {
        String className = getClass().getSimpleName();
        String text = className+"(";
        for (Node<K,V> p = first; p != null; p = p.next) {
            text +=  p.key +"->" + p.value + (p.next != null ? "," : "");
        }
        return text + ")";
    }

    // Almost an iterator on nodes in linked structure
    private class NodeIterator {
        Node<K,V> current;

        public NodeIterator() {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Node<K, V> nextNode() {
            if(!hasNext())
                throw new NoSuchElementException();
            Node<K,V> node = current;
            current = current.next;
            return node;
        }
    }

    private class KeyIterator extends NodeIterator implements Iterator<K> {
        @Override
        public K next() {
            return super.nextNode().key;
        }
    }

    private class ValueIterator extends NodeIterator implements Iterator<V> {
        @Override
        public V next() {
            return super.nextNode().value;
        }
    }

    private class KeyValueIterator extends NodeIterator implements Iterator<Tuple2<K,V>> {
        @Override
        public Tuple2<K,V> next() {
            Node<K,V> node = super.nextNode();
            return new Tuple2<>(node.key, node.value);
        }
    }

    @Override
    public Iterable<K> keys() {
        return new Iterable<K>(){
            public Iterator<K> iterator() {
                return new KeyIterator();
            }
        };
    }

    @Override
    public Iterable<V> values() {
        return new Iterable<V>(){
            public Iterator<V> iterator() {
                return new ValueIterator();
            }
        };
    }

    @Override
    public Iterable<Tuple2<K, V>> keysValues() {
        return new Iterable<Tuple2<K, V>>(){
            public Iterator<Tuple2<K,V>> iterator() {
                return new KeyValueIterator();
            }
        };
    }
}
