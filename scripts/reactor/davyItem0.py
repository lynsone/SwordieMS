# Chest in the Lord Pirate PQ

LORD_PIRATE_ENRAGED_KRU = 9300115
LORD_PIRATE_ENRAGED_CAPTAIN = 9300116
hitCount = 0

def action(type):
    if type == 0:
        global hitCount
        hitCount += 1
        sm.chat(str(hitCount))
        if hitCount >= 1:
            i = 1
            while i < 10:
                sm.spawnMob(LORD_PIRATE_ENRAGED_KRU, sm.getPosition(objectID).getX(), sm.getPosition(objectID).getY(), False)
                sm.spawnMob(LORD_PIRATE_ENRAGED_CAPTAIN, sm.getPosition(objectID).getX(), sm.getPosition(objectID).getY(), False)
                i += 1
            sm.removeReactor()
            sm.dispose()
