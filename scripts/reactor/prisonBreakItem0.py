# Key Chest (921160600) | Drops a key on the map | Used in Escape Party Quest

PRISON_KEY = 4001528
hitCount = 0

def init():
    action(0)

def action(type):
    sm.chat(str(type))
    if type == 0:
        global hitCount
        hitCount += 1
        sm.chat(str(hitCount))
        if hitCount >= 1:
            sm.dropItem(PRISON_KEY, sm.getChr().getPosition().getX(), sm.getChr().getPosition().getY())
            sm.removeReactor()
            sm.dispose()