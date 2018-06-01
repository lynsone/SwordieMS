#   [Job Adv] (Lv.100)   Way of the Buccaneer / Corsair

heroicPentagon = 4031860
heroicStar = 4031861
status = -1

def init():
    if sm.getChr().getLevel() >= 100:
        sm.sendNext("You have accomplished the pinnacle of Strength, however there is one more obstacle in your way.\r\n"
                    "I will test you before I grant you the 4th job powers.")
    else:
        sm.sendSayOkay("You are not ready yet. Talk to me when you are Level 100.")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.sendNext("Bring me one #b#i"+ str(heroicPentagon) +"##z"+ str(heroicPentagon) +"##k and one #b#i"+ str(heroicStar) +"##z"+ str(heroicStar) +"##k. "
                                                                                                                                                       "These tokens of heroism can be obtained by defeating #bManon#k and #bGriffey#k.")

    if status == 1:
        sm.sendAskYesNo("Are you ready to take the test?")

    if status == 2:
        if response == 1:
            sm.sendSayOkay("I will wait for your arrival.")
            sm.startQuestNoCheck(parentID)
        else:
            sm.sendSayOkay("Talk to me once you feel you are ready.")
        sm.dispose()