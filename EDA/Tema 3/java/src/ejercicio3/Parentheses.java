package ejercicio3;

public class Parentheses extends Item{
    @Override
    public boolean isParentheses(){
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        Parentheses p = (Parentheses) obj;

        return p.getClass().equals(this.getClass());
    }
}
