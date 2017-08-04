package command;

public class MulCommand extends Command {

    public String getOperator() {
        return "*";
    }

    protected int execute() {
        return dataStack.pop() * dataStack.pop();
    }

    public boolean isValidate() {
        return dataStack != null && dataStack.size() > 1;
    }
}
