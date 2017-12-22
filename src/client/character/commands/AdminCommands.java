package client.character.commands;

/**
 * Created on 12/22/2017.
 */
public class AdminCommands {

    public class PacketCommand extends AdminCommand {

        @Override
        public void execute(String[] args) {
            System.out.println("je moeder");
        }

        @Override
        public String getName() {
            return "packet";
        }
    }
}
