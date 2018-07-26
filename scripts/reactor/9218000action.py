# Prison Door (9218000) | Used in Escape Party Quest

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
            if sm.hasItem(PRISON_KEY):
                sm.consumeItem(PRISON_KEY)
                sm.removeReactor()
            else:
                sm.chat("You need a key in order to open the Prison.")
            sm.dispose()