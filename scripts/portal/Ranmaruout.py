def init():
    sm.sendAskYesNo("Would you like to leave?")

def action(response, answer):
    if response == 1:
        sm.clearPartyInfo()
        sm.warpPartyOut(807300100)
    sm.dispose()
