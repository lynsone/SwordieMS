# [Root Abyss] An Urgent Summons

NEINHEART = 1404009
SILENT_SWAMP = 105010000 # Map you get warped to after the conversation

status = -1
def init():
    sm.setSpeakerID(NEINHEART)
    sm.sendNext("I've been waiting for you.")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setPlayerAsSpeaker()
        sm.sendNext("What's going on? I was in the middle of very important loot-related business.")

    elif status == 1:
        sm.setSpeakerID(NEINHEART)
        sm.sendNext("The Alliance has received some very shocking news. "
                    "A previously uncharted area has appeared in the northern regions of the Sleepywood.")

    elif status == 2:
        sm.setPlayerAsSpeaker()
        sm.sendNext("Appeared?")

    elif status == 3:
        sm.setSpeakerID(NEINHEART)
        sm.sendNext("Yes, it's quite odd. I believe it was hidden by some sort of old magic.")

    elif status == 4:
        sm.sendNext("The scout who brought me this information said he felt a very evil presence there. "
                    "It could have something to do with the Black Mage.")

    elif status == 5:
        sm.setPlayerAsSpeaker()
        sm.sendNext("Sounds like we need to get over there right away.")

    elif status == 6:
        sm.setSpeakerID(NEINHEART)
        sm.sendNext("I've already dispatched the Cygnus Knights. The topography of the area is complex, and a thick fog covers much of the landscape.")

    elif status == 7:
        sm.setPlayerAsSpeaker()
        sm.sendNext("...What should I do?")

    elif status == 8:
        sm.setSpeakerID(NEINHEART)
        sm.sendNext("Go look around. The loss of one explorer would be far more acceptable than all of the Cygnus Knights.")

    elif status == 9:
        sm.setPlayerAsSpeaker()
        sm.sendNext("Love you too...")

    elif status == 10:
        sm.setSpeakerID(NEINHEART)
        sm.sendNext("I will send you to #b#m"+ str(SILENT_SWAMP) +"##k to investigate the area. "
                    "Report back immediately if you find anything, and try to send up a flare or something if you're going to get yourself killed.")

    elif status == 11:
        sm.sendNext("I'll send you to #b#m"+ str(SILENT_SWAMP) +"##k with Shinsoo's power.")

    elif status == 12:
        sm.warp(SILENT_SWAMP, 0)
        sm.startQuestNoCheck(parentID)
        sm.dispose()