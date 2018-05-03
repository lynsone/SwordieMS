def init():
    sm.sendAskYesNo("Would you like to go back to Victoria Island?")

def action(response, answer):
    if response == 1:
        sm.warp(104020000, 0)
    sm.dispose()