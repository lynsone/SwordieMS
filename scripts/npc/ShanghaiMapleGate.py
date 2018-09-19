response = sm.sendAskYesNo("Would you like to go back to Victoria Island?")

if response == 1:
    sm.warp(104020000, 0)