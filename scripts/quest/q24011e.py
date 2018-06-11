def init():
    if sm.getChr().getJob() == 2300:
        sm.jobAdvance(2310)
        sm.addSP(5)
        sm.completeQuest(24011)
    sm.dispose()