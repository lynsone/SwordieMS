# 921160700 - Escape! - PQ
def init():
    if sm.hasMobsInField():
        sm.chat("Eliminate the boss before continuing")
    else:
        sm.teleportToPortal(6)
    sm.dispose()