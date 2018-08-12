# Special Training Master

medal = 1142246

def init():
    if sm.canHold(medal):
        sm.startQuestNoCheck(parentID)
        sm.completeQuestNoRewards(parentID)
    sm.dispose()