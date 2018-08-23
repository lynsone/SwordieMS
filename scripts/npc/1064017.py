# Vellum NPC (1064017) | Used for the Root Abyss Quest Line

VELLUM = 1064017
status = -1
def init():
    if sm.hasQuest(30006):
        sm.setSpeakerID(VELLUM)
        sm.sendNext("Foolish creature! You dare challenge #rhis#k will?!")
    else:
        sm.dispose()

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setPlayerAsSpeaker()
        sm.sendNext("What are you?!")

    elif status == 1:
        sm.setSpeakerID(VELLUM)
        sm.sendNext("#rHis#k majesty trusted me to be his Seal Guardian and YOU dare sully his plans. "
                    "I am called #bVellum#k. You will not live to remember it.")

    elif status == 2:
        sm.setPlayerAsSpeaker()
        sm.sendNext("Are you the one who put a seal on the World Tree?")

    elif status == 3:
        sm.setSpeakerID(VELLUM)
        sm.sendNext("The seal was #rhis majesty's#k idea. I am merely acting on his will.")

    elif status == 4:
        sm.setPlayerAsSpeaker()
        sm.sendNext("You keep saying #rhim#k. Are you talking about that demon with the eyepatch?")

    elif status == 5:
        sm.setSpeakerID(VELLUM)
        sm.sendNext("Silence! Your filthy mouth shall not even reference #rhis#k might!")

    elif status == 6:
        sm.setPlayerAsSpeaker()
        sm.sendNext("I'm not looking for a fight. The Demon Slayer is our ally. Why can't you just join us as well?")

    elif status == 7:
        sm.setSpeakerID(VELLUM)
        sm.sendNext("You dare put that filthy traitor on the same level as #rhim#k? I will grant your wish for a slow death!")

    elif status == 8:
        sm.sendNext("I am but one of the four guardians. You stand no chance against any of us. "
                    "Accept your meaningless existence and leave to never return.")

    elif status == 9:
        sm.warp(910700200, 0) # Quest Field (Colossal Root)
        sm.lockInGameUI(False)
        sm.dispose()