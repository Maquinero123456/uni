package ejercicio2;

import dataStructures.stack.ArrayStack;

public class PostFix {
    public static void main(String[] args) {
        Item [] sample = {
                new Data(5),
                new Data(6),
                new Data(2),
                new Dif(),
                new Data(3),
                new Mul(),
                new Add() };

        System.out.println(evaluate(sample));
    }

    public static int evaluate(Item[] exprList){
        ArrayStack<Integer> s = new ArrayStack<>();

        for(int i = 0; i<exprList.length; i++){
            if(exprList[i].isData()){
                s.push(exprList[i].getValue());
            }else if (exprList[i].isOperation()){
                int a1= s.top();
                s.pop();
                int a2= s.top();
                s.pop();
                s.push(exprList[i].evaluate(a2, a1));
            }
        }

        return s.top();
    }
}
