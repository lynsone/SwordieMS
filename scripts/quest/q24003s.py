# Peaceful Music  ( Mercedes Intro )
if sm.hasQuest(24000): # Astilda's Request
    sm.startQuestNoCheck(parentID)
    sm.completeQuestNoRewards(parentID)
    sm.completeQuestNoRewards(24000) # Astilda's Request
sm.dispose()
