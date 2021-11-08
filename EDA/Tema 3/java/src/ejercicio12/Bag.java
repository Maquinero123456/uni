package ejercicio12;

public interface Bag<T> extends Iterable<T> {

    boolean isEmpty();

    int size();

    void insert(T x);

    boolean isElem(T x);

    void delete(T x);
}
