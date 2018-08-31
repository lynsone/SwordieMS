# 270010300
def init():
    if not sm.hasQuestCompleted(3503): # time lane quest
        sm.chat("You have not completed the appropriate quest to enter here.")
    else:
        sm.warp(270010400, 5)
    sm.dispose()