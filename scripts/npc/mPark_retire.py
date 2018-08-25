# Spiegelmann (9071005) | In Monster Park Maps
def init():
    sm.sendAskYesNo("Do you want to leave?")

def action(response, answer):
    if response == 1:
        sm.warpInstanceOut(951000000)
    sm.dispose()