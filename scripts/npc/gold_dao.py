# Dao (9000080) | Ravana's Golden Altar

def init():
    if sm.getFieldID() == 252030100:
        sm.sendAskYesNo("Would you like to leave?")
    else:
        sm.dispose()

def action(response, answer):
    if response == 1:
        sm.warpPartyOut(252030000)
    sm.dispose()