#Demon's Doorway (9201132) | Ribbon Pig Beach

quest = 28256       # Treasure, and Crocell the Demon  Quest
mapid = 677000006   # Crocell Strolling Path (Map before Boss Map)

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