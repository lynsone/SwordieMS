# Pretty Angel Power

medal = 1142495

def init():
    if sm.canHold(medal):
        sm.chat("You have earned a new medal.")
        sm.giveItem(medal)
        sm.startQuestNoCheck(parentID)
        sm.completeQuestNoRewards(parentID)
    sm.dispose()