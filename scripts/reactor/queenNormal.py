hitCount = 0

def init():
    action(0)

def action(type):
    sm.chat(str(type))
    if type == 0:
        global hitCount
        hitCount += 1
        sm.chat(str(hitCount))
        if hitCount == 5:
            sm.spawnMob(8920100, 37, 135, False)
            sm.removeReactor()
            sm.dispose()