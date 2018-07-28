# To close the door in Lord Pirate PQ
OLD_METAL_KEY = 4001117
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
            if sm.hasItem(OLD_METAL_KEY):
                sm.consumeItem(OLD_METAL_KEY)
                sm.removeReactor()
            sm.dispose()