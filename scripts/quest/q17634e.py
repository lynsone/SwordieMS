# [Commerci Republic] A Chat with Gilberto

status = -1
COMMERCI_BOOTS = 1072874

def init():
    sm.setSpeakerID(9390256) # Leon Daniella
    sm.sendNext("Whao, my dad has, like, so much faith in me. It's incredible, right?")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("Yeah, just try to tone it down in battle, okay? Don't rush into situations that are obviously traps.")

    elif status == 1:
        sm.setSpeakerID(9390256) # Leon Daniella
        sm.sendNext("Why not?")

    elif status == 2:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("Your dad would've been upset to learn that some strange girl helped us...")

    elif status == 3:
        sm.setSpeakerID(9390256) # Leon Daniella
        sm.sendNext("Her cloak is so mysterious, don't you think? it just oozes, like, mystery.")

    elif status == 4:
        if sm.canHold(COMMERCI_BOOTS):
            sm.sendNext("Oh, and you can have this. I don't want it.")
            sm.giveItem(COMMERCI_BOOTS)
            sm.completeQuest(parentID)
        else:
            sm.sendNext("I was going to give you some fancy boots, but I see you're carrying to much..\r\n"
                        "Why don't you drop some of your garbage?")
        sm.dispose()