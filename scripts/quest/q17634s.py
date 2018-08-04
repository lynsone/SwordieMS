# [Commerci Republic] A Chat with Gilberto

status = -1

def init():
    sm.setSpeakerID(9390203) # Gilberto Daniella
    sm.sendNext("So, what happened to the delfinos?")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("We defeated the Delfinos. They won't be causing any trouble for a while.")

    elif status == 1:
        sm.setSpeakerID(9390203) # Gilberto Daniella
        sm.sendAskYesNo("Really? As easy as that? Did you encounter any difficulties along the way?")

    elif status == 2:
        sm.setSpeakerID(9390256) # Leon Daniella
        sm.sendNext("It got tougher over time, but nothing we couldn't handle")

    elif status == 3:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("To be totally honest, sir. Leon did a great job leading the squad.")

    elif status == 4:
        sm.setSpeakerID(9390256) # Leon Daniella
        sm.sendNext("(Psst, shouldn't we tell him about HER?)")

    elif status == 5:
        sm.setSpeakerID(9390203) # Gilberto Daniella
        sm.sendNext("I know my son had it in him, buried somewhere deep deep deep deep deep deep deep deep "
                        "deep deep deep deep deep deep deep deep inside.")

    elif status == 6:
        sm.setSpeakerID(9390203) # Gilberto Daniella
        sm.sendNext("Maybe you're ready for more responsibility, Leon")

    elif status == 7:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("Oh, we also found a strange barrier on our way back.")

    elif status == 8:
        sm.setSpeakerID(9390203) # Gilberto Daniella
        sm.sendNext("What do you mean, 'barrier'?")

    elif status == 9:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("We're not sure. We didn't think it would be wise to investigate further without consulting with you first.")

    elif status == 10:
        sm.setSpeakerID(9390203) # Gilberto Daniella
        sm.sendNext("I'll send some men out there to check it out. Go rest now. You've earned it.")
        sm.startQuest(parentID)
        sm.dispose()