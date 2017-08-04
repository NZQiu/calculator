package command;

import java.util.Stack;

public class ClearCommand extends Command {

    public String getOperator() {
        return "clear";
    }

    protected int execute() {
        return 0;
    }

    @Override
    public Stack<Integer> calculate() {
        return new Stack<Integer>();
    }

    public boolean isValidate() {
        return dataStack != null;
    }
}
