# Dao (9000080) | Ravana's Golden Altar

if sm.getFieldID() == 252030100:
    response = sm.sendAskYesNo("Would you like to leave?")
else:
    sm.dispose()

if response == 1:
    sm.warpPartyOut(252030000)
sm.dispose()
