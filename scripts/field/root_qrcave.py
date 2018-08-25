# Root Abyss : Abyssal Cave (910700300)  Map for Root Abyss Quest Line

VELLUM = 1064017
status = -1
def init():
    if sm.hasQuest(30006):
        sm.lockInGameUI(True)
        sm.spawnNpc(VELLUM, 350, 443)
        sm.dispose()
        sm.openNpc(VELLUM)
    else:
        sm.dispose()