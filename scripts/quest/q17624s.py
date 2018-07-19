#[Commerci Republic] Fish out of Water

status = -1
def init():
    sm.setSpeakerID(9390256) #Leon Daniella
    sm.sendNext("Sigh..")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setSpeakerID(0) #Has to be Player Avatar
        sm.sendNext("#b(They seem worried.)#k\r\n"
                    "Morning Leon. Good morning Gilberto.")

    elif status == 1:
        sm.setSpeakerID(9390256) #Leon Daniella
        sm.sendNext("Oh, #h0#, my sidekick. You're awake.")

    elif status == 2:
        sm.setSpeakerID(0) #Has to be Player Avatar
        sm.sendNext("#b(How should I approach this...)#k\r\n"
                    "Did you sleep well Leon? Is something the matter? Gilberto, you look worried as well.")

    elif status == 3:
        sm.setSpeakerID(9390256) #Leon Daniella
        sm.sendNext("The Commerci Republic is known for it's commerce. The sea trade is great, but the land trade is weak.")

    elif status == 4:
        sm.setSpeakerID(0) #Has to be Player Avatar
        sm.sendNext("Huh?")

    elif status == 5:
        sm.setSpeakerID(9390203) #Gilberto Daniella
        sm.sendNext("Sigh, allow me. Leon still has to work on this speaking skills.")

    elif status == 6:
        sm.sendNext("The #bDelfinos#k are responsible! They prey on our good merchants from the San Commerci canals!")

    elif status == 7:
        sm.sendNext("It has forced us to isolate ourselves. "
                    "It's costing me more than a pretty penny, let me tell you.")

    elif status == 8:
        sm.sendNext("#b(Delfino? I don't think they're in Maple World, but either way they seem to be causing trouble. "
                    "This might be my chance to earn Gilberto's trust, so I should inquire")

    elif status == 9:
        sm.completeQuest(parentID)
        sm.dispose()