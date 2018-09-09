# Demon's Doorway (9201129) | Close to the Sky

quest = 28198   # Treasure, and Marbas the Demon  Quest
mapid = 677000000   # Marbas Strolling Path (Map before Boss Map)

if sm.hasQuest(quest):
    response = sm.sendAskYesNo("Would you like to enter?")
else:
    sm.sendSayOkay("(A strange doorway)")
    sm.dispose()

if response == 1:
    sm.warp(mapid, 0)
sm.dispose()
