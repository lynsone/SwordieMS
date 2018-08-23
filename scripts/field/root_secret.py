# Secret Swamp (Part of Root Abyss Quest Line)
def init():
    if sm.hasQuest(30000):
        sm.lockInGameUI(True)
        sm.setPlayerAsSpeaker()
        sm.invokeAfterDelay(5000, "lockInGameUI", False)
        sm.invokeAfterDelay(3000, "sendNext", "This fog is too thick! I gotta keep my senses sharp. Who knows what's lurking out there...")

    elif sm.hasQuest(30003):
        sm.setPlayerAsSpeaker()
        sm.sendNext("Hmm.. It seems to work fine. Let's go back to tell her about the exit.")

    else:
        sm.dispose()

def action(response, answer):
    sm.dispose()