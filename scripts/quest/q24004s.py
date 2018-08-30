# Barriers of Elluel

def init():
    sm.setSpeakerID(1033205) # Entrance to Elluel NPC
    sm.sendAskYesNo("#b(You can see the wards to create the seal around Elluel. \r\n"
                    "Speaking the magic word will finisht eh spell, cutting the village off from the outside world for at least 100 years. \r\n"
                    "Activate the seal?)#k")

def action(response, answer):
    if response == 1:
        sm.startQuestNoCheck(parentID)
        sm.completeQuestNoRewards(parentID)
        sm.sendSayOkay("#b(The seal is complete, and the town is safe.)")
    sm.dispose()