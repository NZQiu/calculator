package command;

public class DivCommand extends Command {

    protected int execute() {
        return dataStack.pop() / dataStack.pop(); // TODO: divide 0 check;
    }

    public boolean isValidate() {
        return dataStack != null && dataStack.size() == 2;
    }
}
