# [Root Abyss] The World Girl

MYSTERIOUS_GIRL = 1064001 # npc Id
status = -1
def init():
    sm.setPlayerAsSpeaker()
    sm.sendNext("How in the world did you end up here, anyway? It's not exactly the greatest place for a little girl.")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setSpeakerID(MYSTERIOUS_GIRL)
        sm.sendNext("Root Abyss is my land. I created it a long time ago.")

    elif status == 1:
        sm.setPlayerAsSpeaker()
        sm.sendNext("Whaat?! You CREATED this place?")

    elif status == 2:
        sm.setSpeakerID(MYSTERIOUS_GIRL)
        sm.sendNext("I picked this land because it was fertile and hidden away."
                    "I was hoping to restore my powers, but the darkness is too strong now.")

    elif status == 3:
        sm.setPlayerAsSpeaker()
        sm.sendNext("You pretty much sound like a crazy person. Who ARE you?")

    elif status == 4:
        sm.setSpeakerID(MYSTERIOUS_GIRL)
        sm.sendNext("My name is Alicia. Some people call me the #rWorld Tree#k.")

    elif status == 5:
        sm.setPlayerAsSpeaker()
        sm.sendNext("World Tree? YOU are the World Tree?!")

    elif status == 6:
        sm.setSpeakerID(MYSTERIOUS_GIRL)
        sm.sendNext("I was sleeping here to recover from the Black Mage's attacks. This area is really quite rich with life energy.")

    elif status == 7:
        sm.setPlayerAsSpeaker()
        sm.sendNext("I still can't believe you're a tree.")

    elif status == 8:
        sm.startQuest(parentID)
        sm.dispose()