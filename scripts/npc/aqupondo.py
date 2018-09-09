# Leon Daniella (9390234) | Canal Battleground 5
if sm.getFieldID() == 865020051:
    response = sm.sendAskYesNo("Would you like to return to #b#m865020300##k?")

if response == 1:
    sm.warpInstanceOut(865020300)
sm.dispose()
