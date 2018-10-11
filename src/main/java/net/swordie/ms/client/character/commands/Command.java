package net.swordie.ms.client.character.commands;

import net.swordie.ms.enums.AccountType;

public @interface Command {

    String[] names();
    AccountType requiredType();

}
