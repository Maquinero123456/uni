package ejercicio2;

public abstract class Item {

    public boolean isData(){
        return false;
    }

    public boolean isOperation( ) {
        return false;
    }
    public int getValue() {
        throw new UnsupportedOperationException();
    }
    public int evaluate(int a1, int a2) {
        throw new UnsupportedOperationException();
    }

}
