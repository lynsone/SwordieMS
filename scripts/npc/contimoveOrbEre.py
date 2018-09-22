map = 200090020

response = sm.sendAskYesNo("Would you like to go #m" + str (map) + "m#?")

if response == 1:
    sm.warp(map, 0)
