THE_MYSTERIOUS_GIRL = 30002

def init():
    if sm.hasQuest(THE_MYSTERIOUS_GIRL):
        sm.setPlayerAsSpeaker()
        sm.sendNext("I should tell that girl about the exit first.")
        sm.completeQuest(THE_MYSTERIOUS_GIRL)
        sm.dispose()
    else:
        sm.warp(105010200, 0) # Secret Swamp