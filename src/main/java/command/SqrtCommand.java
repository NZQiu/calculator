package command;

public class SqrtCommand extends Command {

    public String getOperator() {
        return "sqrt";
    }

    protected int execute() {
        return (int)Math.sqrt(dataStack.pop());
    }

    public boolean isValidate() {
        return dataStack != null && dataStack.size() > 0;
    }
}
