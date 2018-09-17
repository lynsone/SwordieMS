SKILLS = [30010166, 30011167, 30011168, 30011169, 30011170]
ARKARIUM = 2159309

sm.completeQuestNoRewards(23205)
sm.deleteQuest(23205)
for i in range(5):
    if not sm.hasSkill(SKILLS[i]):
        sm.giveSkill(SKILLS[i], 0)# remove the skill
sm.removeNpc(ARKARIUM)
sm.warpInstanceIn(927000070, 0)
sm.dispose()