# Demon's Doorway (9201132) | Ribbon Pig Beach

quest = 28256   # Treasure, and Crocell the Demon  Quest
mapid = 677000006   # Crocell Strolling Path (Map before Boss Map)

if sm.hasQuest(quest):
    response = sm.sendAskYesNo("Would you like to enter?")
else:
    sm.sendSayOkay("(A strange doorway)")
    sm.dispose()

if response == 1:
    sm.warp(mapid, 0)
sm.dispose()
