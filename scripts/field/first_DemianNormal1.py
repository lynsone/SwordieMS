def init():
    sm.spawnMob(8880100, 1073, 16, False, 840000000000) # 840b

def onMobDeath(mobObjId):
    if sm.getChr().getField().getMobs().size() == 0:
        sm.warp(350160240)