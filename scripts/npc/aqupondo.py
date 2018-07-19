#Leon Daniella (9390234) | Canal Battleground 5
def init():
    if sm.getFieldID() == 865020051:
        sm.sendAskYesNo("Would you like to return to #b#m865020300##k?")

def action(response, answer):
    if response == 1:
        sm.warp(865020300, 0)
    sm.dispose()