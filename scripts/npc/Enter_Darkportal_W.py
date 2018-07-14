#Demon's Doorway (9201128) | Big Rocky Road

quest = 28179       # Treasure, and Andras the Demon  Quest
mapid = 677000004   # Andras Strolling Path (Map before Boss Map)

def init():
    if sm.hasQuest(quest):
        sm.sendAskYesNo("Would you like to enter?")
    else:
        sm.sendSayOkay("(A strange doorway)")
        sm.dispose()

def action(response, answer):
    if response == 1:
        sm.warp(mapid, 0)
    sm.dispose()