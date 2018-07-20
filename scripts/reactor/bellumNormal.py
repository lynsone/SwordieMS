hitCount = 0

def init():
    action(0)

def action(type):
    sm.chat(str(type))
    if type == 0:
        # global hitCount
        # hitCount += 1
        # sm.chat(str(hitCount))
        # if hitCount >= 1:
        sm.spawnMob(9400942, -200, 440, False)  # NPE at Foothold
        sm.removeReactor()
        sm.dispose()