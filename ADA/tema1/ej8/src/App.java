public class App {
    public static void main(String[] args) throws Exception {
        try {
            System.out.println(Integer.parseInt(args[0])/Integer.parseInt(args[1]));
        } catch (ArithmeticException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }
}
