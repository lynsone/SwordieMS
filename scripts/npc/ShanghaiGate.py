def init():
    sm.sendAskYesNo("Would you like to travel to Shanghai?")

def action(response, answer):
    if response == 1:
        sm.warp(701102000, 0)
    sm.dispose()