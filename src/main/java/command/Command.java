package command;

import java.util.Stack;

public abstract class Command {

    Stack<Integer> dataStack;

    protected abstract int execute();

    public abstract boolean isValidate();

    public Stack<Integer> calculate() {
        int result = execute();
        dataStack.push(result);
        return dataStack;
    }

    public Stack<Integer> undo() {
        return this.dataStack;
    }

    public void setDataStack(Stack<Integer> stack) {
        this.dataStack = stack;
    }

}
