#   [Job Adv] (Lv.100)   Way of the NightLord / Shadower

heroicPentagon = 4031517
heroicStar = 4031518
status = -1

def init():
    sm.setSpeakerID(2081400) # Hellin
    sm.sendNext("You have returned.")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.sendNext("I will take these tokens of heroism from you, and grant you your 4th job skills.\r\nYou helped a great deal in the fight to come.")

    if status == 1:
        sm.consumeItem(heroicPentagon, 1)
        sm.consumeItem(heroicStar, 1)
        sm.completeQuestNoRewards(parentID)
        chrJobID = sm.getChr().getJob()
        sm.jobAdvance(chrJobID+1)
        sm.dispose()