# Secret Swamp (Part of Root Abyss Quest Line)

status = -1
def init():
    if sm.hasQuest(30000):
        sm.lockInGameUI(True)
        sm.sendDelay(3000)

    elif sm.hasQuest(30003):
        sm.setPlayerAsSpeaker()
        sm.sendNext("Hmm.. It seems to work fine. Let's go back to tell her about the exit.")
        sm.dispose()

    else:
        sm.dispose()

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setPlayerAsSpeaker()
        sm.sendNext("This fog is too thick! I gotta keep my senses sharp. Who knows what's lurking out there...")

    elif status == 1:
        sm.lockInGameUI(False)