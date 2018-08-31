# Afrien NPC (1033201) | Used for Mercedes storyline

status = -1
def init():
    sm.setPlayerAsSpeaker()
    sm.sendNext("Afrien? Freud? Are you okay?!")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setSpeakerID(1033201)
        sm.sendSay("Mercedes... You survived.")

    elif status == 1:
        sm.setPlayerAsSpeaker()
        sm.sendSay("Of course! I managed to seal him away. I can't let myself die after that! But what about you? And the others? Where are they?")

    elif status == 2:
        sm.setSpeakerID(1033201)
        sm.sendSay("We may have #bdefeated the Black Mage#k, but he sent everyone flying in different directions with that last spell. We're lucky we ended up in the same place.")

    elif status == 3:
        sm.setPlayerAsSpeaker()
        sm.sendSay("You're right... I didn't realize how far away we ended up. At least we're safe.")

    elif status == 4:
        sm.setPlayerAsSpeaker()
        sm.sendSay("Now that the fight is over, I feel so weak... Not just that, but i feel so cold...")

    elif status == 5:
        sm.setPlayerAsSpeaker()
        sm.sendSay("Come to think of it, has it always been snowy here? There's all this heat, and yet snow is falling... Strange...")

    elif status == 6:
        sm.setSpeakerID(1033201)
        sm.sendSay("You can't feel it, Mercedes? The #rgreat curse#k... It's been placed upon you, Freud, and the others.")

    elif status == 7:
        sm.setPlayerAsSpeaker()
        sm.sendSay("C-curse?")

    elif status == 8:
        sm.setSpeakerID(1033201)
        sm.sendSay("There's an icy cold curse clinging to you. You might have been able to shrug it off if you weren't weak from fighting the Black Mage. It looks like he's not letting us off so easily...")

    elif status == 9:
        sm.setPlayerAsSpeaker()
        sm.sendSay("You should be able to survive it, at least. But I'm worried about Freud... He's too weak.")

    elif status == 10:
        sm.setSpeakerID(1033201)
        sm.sendSay("I'll take care of him. For now, I'm more worried about you, Mercedes. #bYou're the ruler of the Elves#k. If the curse is on you, #rwon't it be placed upon all of the Elves#k?")

    elif status == 11:
        sm.setPlayerAsSpeaker()
        sm.sendSay("...!")

    elif status == 12:
        sm.setSpeakerID(1033201)
        sm.sendSay("Hurry back to #bElluel#k. If the #bBlack Mage's curse is on all of the Elves#k, then you must return to your people.")

    elif status == 13:
        sm.setPlayerAsSpeaker()
        sm.sendSay("All right! Afrien... We will meet again!")

    elif status == 14:
        sm.setSpeakerID(1033201)
        sm.sendSay("...I pray you're right.")

    elif status == 15:
        sm.setPlayerAsSpeaker()
        sm.sendAskYesNo("#b(The other heroes will make it through somehow. For now, return to town using your return skill.)")

    elif status == 16:
        if response == 1:
            sm.warpInstanceIn(910150001, 0)
        sm.dispose()