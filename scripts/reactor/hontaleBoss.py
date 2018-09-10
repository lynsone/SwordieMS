
def action(reactor, type):
    sm.chat("type = " + str(type) + ", count = " + str(reactor.getHitCount()))
    if type == 80 or type == 49:
        reactor.incHitCount()
        if reactor.getHitCount() >= 4:
            sm.chat("BOOM Horntail spawns")
            sm.spawnMob(8810002, 95, 260, False)
            sm.spawnMob(8810003, 95, 260, False)
            sm.spawnMob(8810004, 95, 260, False)
            sm.spawnMob(8810005, 95, 260, False)
            sm.spawnMob(8810006, 95, 260, False)
            sm.spawnMob(8810007, 95, 260, False)
            sm.spawnMob(8810008, 95, 260, False)
            sm.spawnMob(8810009, 95, 260, False)
            sm.removeReactor()
            sm.dispose()
