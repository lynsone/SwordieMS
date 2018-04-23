map = 200090600

def init():
    sm.sendAskYesNo("Would you like to go to Edelstein?")

def action(response, answer):
    if response == 1:
        sm.warp(map, 0)
    sm.dispose()