import java.util.ArrayList;

public class prueba {
    public static void main(String[] args) {
        ArrayList<Double> a = new ArrayList<>();
        a.add(5.0);
        ArrayList<Double> c = new ArrayList<>();
        c.add(8.0);
        a.addAll(c);
        ArrayList<ArrayList<Double>> b = new ArrayList<>();
        b.add(a);
        System.out.println(b);
    }
}
