# Root Abyss - Temporal Crevasse (Von Bon's Map)

def onMobDeath(mob):
    if mob.getTemplateId() == 9303154 and sm.hasQuest(30009):
        sm.completeQuest(30009) #[Root Abyss] Defeat the First Guardian