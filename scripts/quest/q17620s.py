# [Commerci Republic] Eye for an Eye

status = -1
def init():
    sm.setSpeakerID(9390225) # Tepes
    sm.sendNext("Now, what dream can I make come true for you? Remember, anything in the entire world is yours for the asking.")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("Can you introduce me to Gilberto Daniella?")

    elif status == 1:
        sm.setSpeakerID(9390225) # Tepes
        sm.sendNext("I offer to make your wildest dreams coe true, and that is what you want?")

    elif status == 2:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("Yup, I really want to meet Gilberto Daniella.")

    elif status == 3:
        sm.setSpeakerID(9390225) # Tepes
        sm.sendNext("I heard you the first time, it's just...")

    elif status == 4:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("What?")

    elif status == 5:
        sm.setSpeakerID(9390225) # Tepes
        sm.sendNext("Well, I thought you'd ask for something difficult, like borrowing my hat.")

    elif status == 6:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("That was next on my list.")

    elif status == 7:
        sm.setSpeakerID(9390225) # Tepes
        sm.sendAskYesNo("To get to the Daniella merchant Union Office, head east from this spot, past the town fountain. "
                        "It's the white building with golden ornamentation.")

    elif status == 8:
        if response == 1:
            sm.setSpeakerID(9390225) # Tepes
            sm.sendNext("I'll let them know you're on your way. Be polite when you talk to Gilberto. "
                        "He is quite powerful in Commerci.")
            sm.startQuestNoCheck(parentID)
        sm.dispose()