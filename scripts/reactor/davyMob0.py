# Chest in the  Lord Pirate Party Quest

LORD_PIRATE_GINSENG_JAR = 9300109
LORD_PIRATE_BELLFLOWER = 9300110
hitCount = 0

def action(type):
    if type == 0:
        global hitCount
        hitCount += 1
        sm.chat(str(hitCount))
        if hitCount >= 1:
            i = 1
            while i < 5:
                sm.spawnMob(LORD_PIRATE_GINSENG_JAR, sm.getPosition(objectID).getX(), sm.getPosition(objectID).getY(), False)
                sm.spawnMob(LORD_PIRATE_BELLFLOWER, sm.getPosition(objectID).getX(), sm.getPosition(objectID).getY(), False)
                i += 1
            sm.removeReactor()
            sm.dispose()
