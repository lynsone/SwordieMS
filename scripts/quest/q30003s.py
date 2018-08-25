# [Root Abyss] Root Ruckus 1

MYSTERIOUS_GIRL = 1064001 # npc Id
status = -1
def init():
    sm.setSpeakerID(MYSTERIOUS_GIRL)
    sm.sendNext("Did you find a way out?")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setPlayerAsSpeaker()
        sm.sendNext("There's an exit not too far from here.")

    elif status == 1:
        sm.setSpeakerID(MYSTERIOUS_GIRL)
        sm.sendNext("I've already tried that about a hundred times, but I can't get out.")

    elif status == 2:
        sm.setPlayerAsSpeaker()
        sm.startQuest(parentID)
        sm.sendNext("Uh... well it worked for me. Let me go check it out.")

    elif status == 3:
        sm.setSpeakerID(MYSTERIOUS_GIRL)
        sm.sendNext("Please be quick. I really don't like it here...")
        sm.dispose()