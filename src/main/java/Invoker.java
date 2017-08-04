import command.*;

import java.util.ArrayList;
import java.util.List;

public class Invoker {
    public static void main(String[] args) {

        List<Command> allowedCommands =
                new ArrayList<Command>(6);
        allowedCommands.add(new AddCommand());
        allowedCommands.add(new SubCommand());
        allowedCommands.add(new MulCommand());
        allowedCommands.add(new DivCommand());
        allowedCommands.add(new SqrtCommand());
        allowedCommands.add(new ClearCommand());

        Calculator calculator = Calculator.getInstance();
        calculator.setAllowedCommand(allowedCommands);

        String inputStream = "1 2 + 3 + 8 * 30 -";
        String[] arr = inputStream.split("\\s+");

        for (int i = 0; i < arr.length; i++) {
            calculator.push(arr[i]);
        }
    }
}
