

public class Invoker {
    public static void main(String[] args) {
        String inputStream = "1 2 * 3 + 8 - 30 /";
        String[] arr = inputStream.split("\\s+");

        Calculator calculator = Calculator.getInstance();

        for (int i = 0; i < arr.length; i++) {
            calculator.push(arr[i]);
        }
    }
}
