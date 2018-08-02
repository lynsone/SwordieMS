# Black Heaven Inside: Core (350060180) & (350060200)  |  Stage 2 & 3 Lotus Boss  |  Used to show Lotus HP as well as warp party into the next map upon Lotus' death

from net.swordie.ms.scripts import ScriptType

LOTUS = 8240104 # Stage 2
LOTUS_2 = 8240105 # Stage 3

def init():
    if sm.getFieldID() == 350060180:
        sm.showHP(LOTUS)
    elif sm.getFieldID() == 350060200:
        sm.showHP(LOTUS_2)

def onMobDeath(mob):
    if mob.getTemplateId() == LOTUS:
        sm.showFieldEffect("Map/Effect2.img/blackHeavenBossDie2") # Shows the Boss Death effect
        sm.invokeAfterDelay(6500, "warp", 350060200, 0) # Warps player(s) into the next map after 6500ms delay

    elif mob.getTemplateId() == LOTUS_2:
        sm.showFieldEffect("Map/Effect2.img/blackHeavenBossDie3", 4700) # Shows the Boss Death effect

    sm.stopEventsByScriptType(ScriptType.FIELD) #Stops the FixedRate Event from the Field Script
    sm.dispose()