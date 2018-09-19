hitCount = 0

def action(reactor, type):
    if type == 0:
        # global hitCount
        # hitCount += 1
        # sm.chat(str(hitCount))
        # if hitCount >= 1:
        sm.spawnMob(9303154, -135, 455, False)
        sm.removeReactor()
        sm.dispose()
