# [Root Abyss] World Tree In Danger

MYSTERIOUS_GIRL = 1064001 # npc Id
status = -1
def init():
    sm.setSpeakerID(MYSTERIOUS_GIRL)
    sm.sendNext("What happened? You disappeared all of the sudden... I was worried.")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setPlayerAsSpeaker()
        sm.sendNext("They took me away.")

    elif status == 1:
        sm.setSpeakerID(MYSTERIOUS_GIRL)
        sm.sendNext("Who? The same people that trapped me here?")

    elif status == 2:
        sm.setPlayerAsSpeaker()
        sm.sendNext("I think so, yeah. There was this really mean guy that kept threatening me.")

    elif status == 3:
        sm.setSpeakerID(MYSTERIOUS_GIRL)
        sm.sendNext("Are you scared? I thought you were going to help me!")

    elif status == 4:
        sm.setPlayerAsSpeaker()
        sm.sendNext("I'm not scared! I'm just... concerned... about getting beat up. "
                    "Look, I'll be honest, I need some back up.")

    elif status == 5:
        sm.setSpeakerID(MYSTERIOUS_GIRL)
        sm.sendNext("What should we do? I can't help with all this dark energy sapping my powers.")

    elif status == 6:
        sm.setPlayerAsSpeaker()
        sm.sendNext("The Maple Alliance can help.")

    elif status == 7:
        sm.sendNext("I'll head to Ereve right away. If anybody can figure out how to unseal Root Abyss. "
                    "It's Neinheart. He might even be mildly amused to see me alive.")

    elif status == 8:
        sm.setSpeakerID(MYSTERIOUS_GIRL)
        sm.sendNext("Okay... but come back fast, okay? I can feel the darkness draining my life away... "
                    "I don't know how long I have left.")

    elif status == 9:
        sm.sendNext("I'll be back before you know it. Just stay strong.")
        sm.startQuest(parentID)
        sm.warp(130000000, 0) #Ereve
        sm.dispose()