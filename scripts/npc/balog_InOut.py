#Mu Young (1061018) | Balrog Boss Map
def init():
    sm.sendAskYesNo("If you leave now, you'll have to start over.\r\n"
                    "Are you sure you want to leave?")

def action(response, answer):
    if response == 1:
        sm.clearPartyInfo(105200000)
    sm.dispose()