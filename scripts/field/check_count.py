# Golden Temple | Ravana's Golden Altar (252030100)

def onMobDeath(mob):
    if mob.getTemplateId() == 8800200 and sm.hasQuest(3863):
        sm.completeQuestNoRewards(3863) # Ravana the Demon
    sm.dispose()