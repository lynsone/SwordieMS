# Hidden Street : Temple of Time Corridor (927020010) | Luminous Intro

VOID_PRIEST = 9300529

coordinates = [
[500, 69],
[550, 69],
[650, 69],
[700, 69],
[900, 69],
[1000, 69],
[1100, 69],
[1200, 69],
[1300, 69],
[1400, 69],
]

sm.lockInGameUI(True)

sm.showBalloonMsg("Effect/Direction8.img/effect/tuto/BalloonMsg0/2", 4000)
sm.forcedMove(False, 200)
sm.invokeAfterDelay(5000, "showBalloonMsg", "Effect/Direction8.img/effect/tuto/BalloonMsg0/3", 4000)
sm.invokeAfterDelay(9000, "showBalloonMsg", "Effect/Direction8.img/effect/tuto/BalloonMsg0/4", 5000)

sm.invokeAfterDelay(15000, "lockInGameUI", False)

for coord in coordinates:
    random = sm.getRandomIntBelow(2)
    sm.spawnMob((9300529 + random), coord[0], coord[1], False)
