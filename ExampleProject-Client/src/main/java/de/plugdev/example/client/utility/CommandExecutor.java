package de.plugdev.example.client.utility;

public abstract class CommandExecutor {

    private final String[] listenTo;

    public CommandExecutor(String[] listenTo) {
        this.listenTo = listenTo;
    }

    protected abstract void execute(String[] input);

    public String[] getAliases() {
        return listenTo;
    }
}
