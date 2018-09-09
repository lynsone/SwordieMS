sm.spawnMob(8880100, 1073, 16, False, 840000000000) # 840b

def onMobDeath(mob):
    if sm.getChr().getField().getMobs().size() == 0:
        sm.warp(350160240)
