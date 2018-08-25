# [Root Abyss] World Tree Rescue

ALICIA = 1064002 # npc Id
status = -1
def init():
    sm.lockInGameUI(True)
    sm.setSpeakerID(ALICIA)
    sm.showFieldEffect("Map/Effect.img/rootabyss/undo")
    sm.showFieldBackgroundEffect("Effect/Direction11.img/effect/meet/frame0/0")
    sm.invokeAfterDelay(4000, "sendNext", "Thank you so much, I can finally go wherever I want!")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setPlayerAsSpeaker()
        sm.sendNext("Oh geez... #b(Neinheart wants me to take her to Ereve, but she looks SO HAPPY to be free. What do I do?)#k")

    elif status == 1:
        sm.sendNext("Heeey, so congratulations on your newfound freedom. But, uh, I think you should go stay in Ereve for a while.")

    elif status == 2:
        sm.setSpeakerID(ALICIA)
        sm.sendNext("But...")

    elif status == 3:
        sm.setPlayerAsSpeaker()
        sm.sendNext("Just for a while, you know? The Empress and Shinsoo can protect you until you get your powers back. "
                    "It's really dangerous out in the world right now and I don't want to see you get hurt. "
                    "But it's all up to you..")

    elif status == 4:
        sm.setSpeakerID(ALICIA)
        sm.sendNext("No... they're right. I am too important to risk wandering around as I please. I'll go to Ereve... "
                    "I just hope they let me go on vacation sometimes.")

    elif status == 5:
        sm.setPlayerAsSpeaker()
        sm.sendNext("I'll make sure they do! I'll even beat up Neinheart if I have to!")

    elif status == 6:
        sm.setSpeakerID(ALICIA)
        sm.sendNext("Hehehe, okay. Once my powers are fully restored, you and me will blow out of there on a whirlwind of tourism and fun!")

    elif status == 7:
        sm.setPlayerAsSpeaker()
        sm.sendNext("You've got yourself a deal. The Cygnus Knights will take you back. I'll see you there.")

    elif status == 8:
        sm.lockInGameUI(False)
        sm.startQuest(parentID)
        sm.warp(913080001, 0) # Fake Cutscene Field (Ereve)