sm.lockInGameUI(True)
sm.forcedInput(1)
sm.sendDelay(30)

sm.forcedInput(0)
sm.showFieldEffect("demonSlayer/text13", 0)
sm.sendDelay(500)

sm.showFieldEffect("demonSlayer/text14", 0)
sm.sendDelay(4000)

sm.lockInGameUI(False)
sm.warpInstanceIn(927000020, 0)

# Should be in ds_tuto_2_prep but java.nio.channels.ClosedByInterruptException
ARKARIUM = 2159309

SKILLS = [30010166, 30011167, 30011168, 30011169, 30011170]

sm.spawnNpc(ARKARIUM, 550, 50)
sm.showNpcSpecialActionByTemplateId(ARKARIUM, "summon", 0)
sm.completeQuestNoRewards(23205)
sm.deleteQuest(23205)
sm.startQuestNoCheck(23204)
for i in range(5):
    if not sm.hasSkill(SKILLS[i]):
        sm.giveSkill(SKILLS[i])

