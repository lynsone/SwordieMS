from net.swordie.ms.scripts import ScriptType

sm.showHP()
sm.dispose()

def onMobDeath(mob):
    sm.stopEventsByScriptType(ScriptType.FIELD)
    sm.dispose()
