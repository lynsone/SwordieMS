if sm.getChr().getJob() == 2310:
    sm.jobAdvance(2311)
    sm.addSP(2)
    sm.setSpeakerID(1033210)
    sm.sendSayOkay("I have advanced you to third job.")
    sm.startQuestNoCheck(parentID)
    sm.completeQuestNoRewards(parentID)
sm.dispose()
