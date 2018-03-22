def init():
    sm.sendAskYesNo("Would you like to go to the guild headquarters?")

def action(response, answer):
    if response == 1:
        sm.warp(200000301, 0)
    sm.dispose()