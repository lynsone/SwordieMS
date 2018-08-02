# Sparkling Crystal - Hidden Street : Dimensional World

def init():
    sm.sendAskYesNo("Do you really want to leave?")

def action(response, answer):
    if response == 1:
        sm.warpInstanceOut(211040401) # Hidden Street : Holy Ground at the Snowfield
    sm.dispose()