# Root Abyss | Queen's Castle (Boss Map)

def onMobDeath(mob):
    if mob.getTemplateId() == 8920100 and sm.hasQuest(30011):
        sm.completeQuest(30011) # [Root Abyss] Defeat the Third Seal Guardian