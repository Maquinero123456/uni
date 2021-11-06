package ejercicio3;
import dataStructures.stack.Stack;
import dataStructures.stack.ArrayStack;

public class InFix {
    static int evaluate(Item[] exprList) {
        Stack<Integer> stackDatas = new ArrayStack<>();
        Stack<Item> stackOperations = new ArrayStack<>();
        for (Item expr : exprList) {
            if (expr.isData()) {
                stackDatas.push(expr.getValue());
            } else if (expr.isOperation()) {
                stackOperations.push(expr);
            } else if (expr.isParentheses()) {
                if(expr.equals(new RightP())){
                    int a1= stackDatas.top();
                    stackDatas.pop();
                    int a2 = stackDatas.top();
                    stackDatas.pop();
                    stackDatas.push(stackOperations.top().evaluate(a2, a1));
                    stackOperations.pop();
                }
            }
        }
        return stackDatas.top();
    }
    public static void main(String [] args) {
        Item[] sample = {
                new LeftP(),
                new LeftP(),
                new Data(4),
                new Mul(),
                new Data(5),
                new RightP(),
                new Dif(),
                new Data(6),
                new RightP() };

        System.out.println(InFix.evaluate(sample));
    }
}
