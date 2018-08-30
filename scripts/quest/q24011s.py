def init():
    sm.setSpeakerID(1033210) # Great Spirit
    sm.sendAskYesNo("Are you ready to take on great power?")

def action(response, answer):
    if response == 1:
        sm.startQuestNoCheck(parentID)
    sm.dispose()