# Awakened Demon

medal = 1142343

if sm.canHold(medal):
    sm.chat("You have earned a new medal.")
    sm.giveItem(medal)
    sm.startQuestNoCheck(parentID)
    sm.completeQuestNoRewards(parentID)
sm.dispose()
