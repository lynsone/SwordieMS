def init():
    sm.sendAskYesNo("Would you like to go to Pantheon?")

def action(response, answer):
    if response == 1:
        sm.warp(400000001, 1)
    sm.dispose()