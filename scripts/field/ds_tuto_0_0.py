# Temple of Time Corridor 1 (927000000)

# Constants
MASTEMA = 2159307
SKILLS = [30011109, 30010110, 30010185]

sm.lockInGameUI(True)
sm.curNodeEventEnd(True)
for i in range(3):
    if not sm.hasSkill(SKILLS[i]):
        sm.giveSkill(SKILLS[i])

sm.forcedInput(0)
sm.spawnNpc(MASTEMA, 1430, 50)
sm.showNpcSpecialActionByTemplateId(MASTEMA, "summon", 0)
sm.showFieldEffect("demonSlayer/back", 0)
sm.showFieldEffect("demonSlayer/text0", 0)
sm.sendDelay(500)

sm.showFieldEffect("demonSlayer/text1", 0)
sm.sendDelay(1000)

sm.forcedInput(2)
sm.sendDelay(3000)

sm.showFieldEffect("demonSlayer/text2", 0)
sm.sendDelay(500)

sm.showFieldEffect("demonSlayer/text3", 0)
sm.sendDelay(4000)

sm.showFieldEffect("demonSlayer/text4", 0)
sm.sendDelay(500)

sm.showFieldEffect("demonSlayer/text5", 0)
sm.sendDelay(4000)

sm.showFieldEffect("demonSlayer/text6", 0)
sm.sendDelay(500)

sm.showFieldEffect("demonSlayer/text7", 0)