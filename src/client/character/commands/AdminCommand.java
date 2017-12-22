package client.character.commands;

/**
 * Created on 12/22/2017.
 */
public abstract class AdminCommand implements Command {

    private static char prefix = '!';

    public static char getPrefix() {
        return prefix;
    }
}
