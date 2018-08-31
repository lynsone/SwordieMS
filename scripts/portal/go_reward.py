# 811000500 - Princess No (pno)
def init():
    sm.sendAskYesNo("Would you like to leave?")

def action(response, answer):
    if response == 1:
        sm.clearPartyInfo(811000100)
    sm.dispose()