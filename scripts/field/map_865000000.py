# San Commerci | Used to complete a Quest:  [Commerci Republic] Ciao, Until Next Time
def init():
    sm.showEffect("Map/EffectBT.img/dawnveil1/temaD") # San Commerci Theme Dungeon Effect

    if sm.hasQuest(17614): # [Commerci Republic] Ciao, Until Next Time
        sm.completeQuest(17614)
    sm.dispose()