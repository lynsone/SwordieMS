# Manual Labor
if not (sm.hasQuest(20034) and sm.hasQuestCompleted(20034)):
    sm.lockInGameUI(True)  
    sm.chatScript("Mr. Limbert's General Store")
    sm.chatScript("Month 3, Day 11")
    sm.forcedInput(2)
    sm.sendDelay(1000)

    sm.forcedInput(1)
    sm.sendDelay(1000)

    sm.showEffect("Effect/Direction7.img/effect/tuto/step0/5", 2000, 0, -100, -100, 0, False, 0)
    sm.sendDelay(2000)

    sm.forcedInput(2)
    sm.sendDelay(500)

    sm.forcedInput(0)
    sm.showEffect("Effect/Direction7.img/effect/tuto/step0/6", 2000, 0, -100, -100, 0, False, 0)
    sm.sendDelay(1000)

    sm.showEffect("Effect/Direction7.img/effect/tuto/step0/4", 2000, 0, -100, -100, 0, False, 0)
    sm.sendDelay(1000)

    sm.showEffect("Effect/Direction7.img/effect/tuto/step0/7", 2000, 0, -100, -100, 0, False, 0)
    sm.sendDelay(2000)

    sm.showEffect("Effect/Direction7.img/effect/tuto/step0/8", 2000, 0, -100, -100, 0, False, 0)
    sm.sendDelay(1000)

    sm.chatScript("Someone suspicious is in the back yard...")
    sm.lockInGameUI(False)  
    sm.startQuestNoCheck(20034)   