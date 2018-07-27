def init():
    sm.spawnMob(8880110, 1073, 16, False, 25200000000000) # 25.2t

def onMobDeath(mob):
    if sm.getChr().getField().getMobs().size() == 0:
        sm.warp(350160240)