# Black Jack - Resistance Headquarters : Secret Plaza
def init():
    sm.sendAskYesNo("Do you want to enter the jaguar habitat?")

def action(response, answer):
    if response == 1:
        sm.warp(931000500, 0)
    sm.dispose()