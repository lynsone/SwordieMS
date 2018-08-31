# 921160700 - Escape! - PQ
from net.swordie.ms.scripts import ScriptType

def init():
    sm.stopEventsByScriptType(ScriptType.FIELD) #Stops the FixedRate Event from the Field Script
    if sm.hasMobsInField():
        sm.chat("The portal is not opened.")
    else:
        sm.warpPartyOut(910002000) # Party Quest Map
        sm.giveExp(sm.getPQExp()) #Gives player PQ exp
    sm.dispose()