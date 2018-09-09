hitCount = 0

action(0)

def action(type):
    sm.chat(str(type))
    if type == 0:
        # global hitCount
        # hitCount += 1
        # sm.chat(str(hitCount))
        # if hitCount >= 1:
        sm.spawnMob(9303154, -135, 455, False)
        sm.removeReactor()
        sm.dispose()
