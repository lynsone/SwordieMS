def init():
    sm.sendAskYesNo("Are you sure you want to leave?")

def action(response, answer):
    #sm.sendSay("Response was " + str(response) + "\r\rAnswer was " + str(answer))
    if response == 1:
        sm.clearPartyInfo()
        sm.warpPartyOut(401060000)
    sm.dispose()
