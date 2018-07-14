# (Lv. 10) Knight-in-Training

trainingKnightMedal = 1142066

def init():
    if sm.canHold(trainingKnightMedal):
        sm.setSpeakerID(1101000)
        sm.sendNext("You are training well! However, you have a lot to learn still. Take this to remember the cause and what it means to be a Knight of Cygnus"
                    "\r\n\r\n1x #v"+ str(trainingKnightMedal) +"##z"+ str(trainingKnightMedal) +"#")
        sm.giveItem(trainingKnightMedal)
        sm.completeQuestNoRewards(parentID)
        sm.dispose()
    else:
        sm.dispose()