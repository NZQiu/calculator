import command.*;

import java.util.*;

public class Calculator {

    private enum State {
        NORMAL,
        WARNING
    }

    private static Calculator instance;

    private static final String UNDO_COMMAND = "undo";
    private static final String ADD_COMMAND = "+";
    private static final String SUB_COMMAND = "-";
    private static final String MUL_COMMAND = "*";
    private static final String DIV_COMMAND = "/";
    private static final String SQRT_COMMAND = "sqrt";
    private static final String CLEAR_COMMAND = "clear";

    private final Map<String, Command> commandMap;
    private final Set<String> allowedCommandSet;

    private State curState;
    private Stack<Integer> dataStack;
    private Stack<Command> commandStack;

    private Calculator() {
        curState = State.NORMAL;

        // Can be injected
        commandMap = new HashMap<String, Command>();
        commandMap.put(ADD_COMMAND, new AddCommand());
        commandMap.put(SUB_COMMAND, new SubCommand());
        commandMap.put(MUL_COMMAND, new MulCommand());
        commandMap.put(DIV_COMMAND, new DivCommand());
        commandMap.put(SQRT_COMMAND, new SqrtCommand());
        commandMap.put(CLEAR_COMMAND, new ClearCommand());

        dataStack = new Stack<Integer>();
        commandStack = new Stack<Command>();

        allowedCommandSet = commandMap.keySet();
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
        return allowedCommandSet.contains(input);
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
