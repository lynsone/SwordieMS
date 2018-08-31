map = 200090701

def init():
    sm.sendAskYesNo("Do you want to go to Edelstein?")

def action(response,answer):
    if response == 1:
        sm.warp(map, 0)
    sm.dispose()