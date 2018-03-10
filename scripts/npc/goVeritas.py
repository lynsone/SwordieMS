def init():
    sm.sendAskYesNo("Would you like to go Veritas?")

def action(response, answer):
    if response == 1:
        sm.warp(230050000,1)
    sm.dispose()