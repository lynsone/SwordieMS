# Adobis - Door to Zakum field
def init():
    sm.sendAskYesNo("Hey, if you want to fight Zakum, I can provide you with some sacrificial orbs.")

def action(response, answer):
    if response == 1:
        if sm.canHold(4001017):
            sm.giveItem(4001017, 5)
        else:
            sm.sendSayOkay("Please make more space in your ETC inventory.")
    else:
        sm.sendSayOkay("Okay, maybe another time.")
    sm.dispose()