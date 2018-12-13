# Inside the Sangri-la  | Explorer tutorial
if not sm.hasMobsInField():
    sm.spawnMob(9300815, -152, 150, False) # Spawn Mano
else:
    sm.killmobs()
    sm.spawnMob(9300815, -152, 150, False) # Spawn Mano
sm.dispose()
