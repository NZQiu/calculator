import command.*;

import java.util.*;

public class Calculator {

    private enum State {
        NORMAL,
        WARNING
    }

    private static Calculator instance;

    private static final String UNDO_COMMAND = "undo";

    private final Map<String, Command> commandMap;
    private final Set<String> allowedOperatorSet;

    private State curState;
    private Stack<Integer> dataStack;
    private Stack<Command> commandStack;

    private Calculator() {
        curState = State.NORMAL;
        commandMap = new HashMap<String, Command>();
        dataStack = new Stack<Integer>();
        commandStack = new Stack<Command>();

        allowedOperatorSet = new HashSet<String>();
        allowedOperatorSet.add(UNDO_COMMAND);
    }

    public static Calculator getInstance() {
        if (instance == null) {
            synchronized (Calculator.class) {
                if (instance == null) {
                    instance = new Calculator();
                }
            }
        }
        return instance;
    }

    // Inject allowed command
    public void setAllowedCommand(List<Command> commands) {
        if (null == commands) return;

        String operator;
        for (Command command : commands) {
            if (null == command) continue;

            operator = command.getOperator();
            if (null == operator)  return;
            if (operator.length() == 0) return;

            allowedOperatorSet.add(operator);
            commandMap.put(operator, command);
        }
    }

    public void push(String input) {
        if (curState == State.WARNING) {
            System.out.println("Operation after warning: Exit");
            return;
        }

        if (isOperand(input)) {
            dataStack.push(Integer.parseInt(input));
        } else if (UNDO_COMMAND.equals(input)){
            undo();
        } else if (isOperator(input)) {
            calculate(input);
        } else {
            errHandle();
        }
        printStack();
    }

    private void calculate(String input) {
        Command command = commandMap.get(input);
        command.setDataStack(this.dataStack);

        if (!command.isValidate()) {
            errHandle();
            return;
        }

        commandStack.push(command);
        this.dataStack = command.calculate();
    }

    private void undo() {
        Command lastCommand = commandStack.pop();

        if (lastCommand == null) {
            errHandle();
            return;
        }
        this.dataStack = lastCommand.undo();
    }

    private boolean isOperand(String input) {
        return input.matches("^-?\\d+$");
    }

    private boolean isOperator(String input) {
        return allowedOperatorSet.contains(input);
    }

    private void printStack() {
        System.out.print("Stack: ");
        for (Integer d : dataStack) {
            System.out.print(d + " ");
        }
        System.out.println("");
    }

    private void errHandle() {
        System.out.println("Invalid operator/operand!");
        curState = State.WARNING;
    }

}
