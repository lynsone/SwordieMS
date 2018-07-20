# Demon's Doorway (9201131) | Blue Mushroom Forest 2

quest = 28238       # Treasure, and Amdusias the Demon  Quest
mapid = 677000002   # Amdusias Strolling Path (Map before Boss Map)

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