# Glacier Cutter Base | 141050000
def init():
    if sm.hasQuest(32187): # [Riena Strait] Glacial Pace
        sm.completeQuestNoRewards(32187) # [Riena Strait] Glacial Pace
    sm.dispose()