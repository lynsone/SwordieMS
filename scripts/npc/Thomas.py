#Thomas | Amoria Ambassador
status = -1

def init():
    if sm.getMap() == 100000000:
        sm.sendAskYesNo("I can take you to Amoria Village. Are you ready to go?")
    else:
        sm.sendAskYesNo("I can take you to back Henesys. Are you ready to go?")

def action(response, answer):
    status += 1

    global status
    if status == 0:
        if response == 1:
            if sm.getMap() == 100000000:
                sm.warp(680000000, 0)
            else:
                sm.warp(100000000, 0)
        else:
            sm.sendSayOkay("Ok, feel free to hang around until you're ready to go!")
        sm.dispose()