package client.character.commands;

/**
 * Created on 12/22/2017.
 */
public interface Command {

    char prefix = '@';
    void execute(String[] args);
    String getName();
    static char getPrefix() {
        return prefix;
    }
}
