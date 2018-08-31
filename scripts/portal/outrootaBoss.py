# 105200310 (+ other RA bosses)
def init():
    sm.sendAskYesNo("Would you like to leave?")

def action(response, answer):
    if response == 1:
        sm.clearPartyInfo(105200000)
    sm.dispose()