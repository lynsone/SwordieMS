# 101020400
if sm.hasQuest(21201):
    sm.warpInstanceIn(914021000, 0)
    sm.createClockForMultiple(15*60, 914021000)
    sm.invokeAfterDelay(6*60*1000, "warpInstanceOut", 140030000, 0)
    sm.completeQuest(21201)
sm.dispose()
