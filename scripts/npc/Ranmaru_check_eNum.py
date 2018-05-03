def init():
    sm.sendAskYesNo("Would you like to enter?")

def action(response, answer):
    if response == 1:
        sm.warp(807300100, 0)
    sm.dispose()