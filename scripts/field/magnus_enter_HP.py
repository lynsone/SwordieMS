from net.swordie.ms.scripts import ScriptType

def init():
    sm.showHP()
    sm.dispose()

def onMobDeath(mob):
    sm.stopEventsByScriptType(ScriptType.FIELD)
    sm.dispose()