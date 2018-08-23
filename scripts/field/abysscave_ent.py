# Root Abyss | Abyssal Cave  (Vellum's Boss Map)

def onMobDeath(mob):
    if mob.getTemplateId() == 9400942 and sm.hasQuest(30012):
        sm.completeQuest(30012) #[Root Abyss] Defeat the Final Guardian