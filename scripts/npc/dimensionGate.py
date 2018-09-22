response = sm.sendAskYesNo("Would you like to go to Pantheon?")

if response == 1:
    sm.warp(400000001, 1)