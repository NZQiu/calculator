package command;

public class DivCommand extends Command {

    public String getOperator() {
        return "+/";
    }

    protected int execute() {
        return dataStack.pop() / dataStack.pop(); // TODO: divide 0 check;
    }

    public boolean isValidate() {
        return dataStack != null && dataStack.size() > 1;
    }
}
