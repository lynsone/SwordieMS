response = sm.sendAskYesNo("Would you like to travel to Shanghai?")

if response == 1:
    sm.warp(701102000, 0)
