# [Commerci Republic] Shipwrecker

status = -1

def init():
    sm.setSpeakerID(9390256) # Leon Daniella
    sm.sendNext("How you feeling, #h0#? I'm so sore. You don't fight an awesome battle without getting a few bruises.")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("I'm all right.")

    elif status == 1:
        sm.setSpeakerID(9390225) # Tepes
        sm.sendNext("Prime Minister! Something awful has happened!")

    elif status == 2:
        sm.setSpeakerID(9390256) # Leon Daniella
        sm.sendNext("What? What's going on?")

    elif status == 3:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("#b(I should go on in, and see what's up.)")

    elif status == 4:
        sm.setSpeakerID(9390203) # Gilberto Daniella
        sm.sendNext("What in the world is it now?")

    elif status == 5:
        sm.setSpeakerID(9390225) # Tepes
        sm.sendNext("Our shop en route to Rosa has been attacked... by Captain Blood!")

    elif status == 6:
        sm.setSpeakerID(9390203) # Gilberto Daniella
        sm.sendNext("Blast it.. That vile man!")

    elif status == 7:
        sm.setSpeakerID(9390225) # Tepes
        sm.sendNext("I think they planned this attack")