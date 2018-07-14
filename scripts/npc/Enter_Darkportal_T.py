#Demon's Doorway (9201130) | Caution Falling Down

quest = 28219       # Treasure, and Valefor the Demon  Quest
mapid = 677000008   # Valefor Strolling Path (Map before Boss Map)

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