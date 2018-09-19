map = 610010000
if sm.getFieldID() == 610010000:
    map = 600000000
response = sm.sendAskYesNo("Would you like to go to #m" + str(map) + "m#?")

if response == 1:
    sm.warp(map, 0)
    sm.dispose()
