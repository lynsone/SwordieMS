LOCK = 9001045
if (not sm.hasQuest(25102)):
    sm.createQuestWithQRValue(25102, "1")
    sm.spawnMob(LOCK, 170, 182, False)
sm.dispose()