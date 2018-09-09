map = 104020000
if sm.getFieldID() != 120040000:
map = 120040000


response = sm.sendAskYesNo("Would you like to go to #m" + str(map) + "#?")

if response == 1:
    sm.warp(map, 0)
sm.dispose()
