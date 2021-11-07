package ejercicio7;

import dataStructures.queue.EmptyQueueException;
import dataStructures.queue.Queue;
import dataStructures.stack.ArrayStack;
import dataStructures.stack.Stack;

public class TwoStackQueue<T> implements Queue<T>{

    ArrayStack<T> x,y;

    //Queue con dos stacks
    public TwoStackQueue(ArrayStack<T> a, ArrayStack<T> b){
        x= new ArrayStack<>();
        y= new ArrayStack<>();
        mkValid(a,b);
    }

    //Queue vacio
    public TwoStackQueue(){
        x= new ArrayStack<>();
        y= new ArrayStack<>();
    }

    private void mkValid(ArrayStack<T> a, ArrayStack<T> b){
        if(a.isEmpty()){
            while(!b.isEmpty()){
                a.push(b.top());
                b.pop();
            }
        }
        x=a;
        y=b;
    }

    @Override
    public boolean isEmpty() {
        return x.isEmpty();
    }

    @Override
    public void enqueue(T x) {
        y.push(x);
    }

    @Override
    public T first() {
        if(x.isEmpty()){
            throw new EmptyQueueException("first en TwoStackQueue vacia");
        }
        return x.top();
    }

    @Override
    public void dequeue() {
        if(x.isEmpty()){
            throw new EmptyQueueException("dequeue en TwoStackQueue vacia");
        }
        x.pop();
        mkValid(x,y);
    }

    @Override
    public String toString() {
        String className = getClass().getName().substring(getClass().getPackage().getName().length()+1);
        return className+"("+x.toString()+", "+y.toString()+")";
    }

    public static void main(String[] args) {
        ArrayStack<Integer> a = new ArrayStack<>();
        ArrayStack<Integer> b = new ArrayStack<>();
        a.push(3);
        a.push(2);
        a.push(1);
        b.push(4);
        b.push(5);

        TwoStackQueue<Integer> c = new TwoStackQueue<>(a,b);
        System.out.println(c);
        System.out.println(c.first());
        c.dequeue();
        c.dequeue();
        c.dequeue();
        c.enqueue(6);
        System.out.println(c);

    }
}
