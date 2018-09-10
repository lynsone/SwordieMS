# Limbert's General Store (913070000) - continue script
sm.forcedInput(0)
sm.showEffect("Effect/Direction7.img/effect/tuto/step0/3", 3000, 0, -100, -100, 0, False, 0)
sm.localEmotion(6, 2000, False)
sm.sendDelay(2000)

sm.sendDelay(1000)

sm.forcedInput(1)
# continue in portal_001.py
sm.dispose()