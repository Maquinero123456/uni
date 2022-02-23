package dataStructures.doubleEndedQueue;
/**
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 *
 * Exceptions for double ended queues
 */


public class EmptyDoubleEndedQueueException extends RuntimeException {

    private static final long serialVersionUID = 5947860083185800772L;

    public EmptyDoubleEndedQueueException() {
        super();
    }

    public EmptyDoubleEndedQueueException(String msg) {
        super(msg);
    }
}
