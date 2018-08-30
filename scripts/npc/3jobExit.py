# Sparkling Crystal - Hidden Street : Dimensional World

def init():
    sm.sendAskYesNo("Do you really want to leave?")

def action(response, answer):
    if response == 1:
        sm.warpInstanceOut(211000001) # El Nath : Chief's Residence
    sm.dispose()