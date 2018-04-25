map = 200090020

def init():
    sm.sendAskYesNo("Would you like to go #m" + str (map) + "m#?")

def action(response, answer):
    if response == 1:
        sm.warp(map, 0)
    sm.dispose()