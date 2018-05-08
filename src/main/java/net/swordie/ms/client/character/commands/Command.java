package net.swordie.ms.client.character.commands;

import net.swordie.ms.client.character.Char;

/**
 * Created on 12/22/2017.
 */
public interface Command {

    char prefix = '@';
    static void execute(Char chr, String[] args){

    }

	static char getPrefix() {
        return prefix;
    }
}
