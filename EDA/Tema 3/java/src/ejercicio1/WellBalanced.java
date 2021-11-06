package ejercicio1;
import dataStructures.stack.*;
public class WellBalanced {
    private final static String OPEN_PARENTHESES ="{[(";
    private final static String CLOSED_PARENTHESES = "}])";
    public static void main(String [] args) {
        ArrayStack<Character> stack = new ArrayStack<>();
        String a = "ff(h([sds)sds]ss)hags";
        System.out.println(wellBalanced(a, stack));
        if(wellBalanced(a, stack)){
            System.out.println(a+" esta balanceado");
        }else{
            System.out.println(a+" no esta balanceado");
        }
    }
    public static boolean wellBalanced(String exp, Stack<Character> stack) {
        for (int i = 0; i < exp.length(); i++){
            if(OPEN_PARENTHESES.indexOf(exp.charAt(i))>0){
                stack.push(exp.charAt(i));
            }else if(CLOSED_PARENTHESES.indexOf(exp.charAt(i))>0 && match(stack.top(), exp.charAt(i))){
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
    public static boolean isOpenParentheses(char c) {
        return OPEN_PARENTHESES.indexOf(new Character(c).toString()) >= 0;
    }
    public static boolean isClosedParentheses(char c) {
        return CLOSED_PARENTHESES.indexOf(new Character(c).toString()) >= 0;
    }
    public static boolean match(char x, char y) {
        return OPEN_PARENTHESES.indexOf(new Character(x).toString()) ==
                CLOSED_PARENTHESES.indexOf(new Character(y).toString());
    }
}