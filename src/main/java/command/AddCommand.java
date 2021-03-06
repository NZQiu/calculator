package command;


public class AddCommand extends Command {

    public String getOperator() {
        return "+";
    }

    protected int execute() {
        return dataStack.pop() + dataStack.pop();
    }

    public boolean isValidate() {
        return dataStack != null && dataStack.size() > 1;
    }
}
