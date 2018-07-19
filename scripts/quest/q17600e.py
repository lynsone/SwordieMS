#[Commerci Republic] Neinheart's call
sm.setSpeakerID(1064026) #Neinheart
def init():
    sm.sendSayOkay("The Empress wishes to speak to you.")
    sm.completeQuest(parentID)
    sm.dispose()