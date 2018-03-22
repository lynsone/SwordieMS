def init():
    sm.sendAskYesNo("Would you like to create a guild? This will cost 5 million mesos.")

def action(response, answer):
    if response == 1:
        if sm.getMesos() < 5000000:
            sm.sendSayOkay("You do not have enough mesos.")
        else:
            sm.showGuildCreateWindow()
    else:
        sm.sendSayOkay("Be sure to come back if you change your mind!")
    sm.dispose()