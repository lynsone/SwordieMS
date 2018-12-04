# Vasily (10305) | Maple Road : Port
if sm.hasQuestCompleted(32214):
    if sm.sendAskYesNo("Thanks to you, we're ready to set sail to Lith Harbor"):
        sm.warp(4000032, 0)
else:
    if sm.sendAskYesNo("I'll let you on board. Go defeat the monsters rampaging my ship."):
        sm.warp(4000033, 0)
sm.dispose()