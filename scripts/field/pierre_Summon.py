PIERRE = 9400938

def init():
    sm.spawnMob(PIERRE, 1155, 551, False)


def onMobDeath(mob):
    if mob.getTemplateId() == PIERRE and sm.hasQuest(30010):
        sm.completeQuest(30010) #[Root Abyss] Defeat the Second Guardian