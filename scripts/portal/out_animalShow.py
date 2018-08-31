# 223030210 - Scarlion & Targa
def init():
    sm.sendAskYesNo("Would you like to leave?")

def action(response, answer):
    if response == 1:
        sm.clearPartyInfo(223030200)
    sm.dispose()