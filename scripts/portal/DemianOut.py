# 350160200 - Portal out damien

def init():
    sm.sendAskYesNo("Are you sure you want to leave?")

def action(response, answer):
    # sm.sendSay("Response was " + str(response) + "\r\rAnswer was " + str(answer))
    if response == 1:
        sm.clearPartyInfo(105300303)
    sm.dispose()
