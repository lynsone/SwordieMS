# Portal at Gelimer's Lab | Used to open an NPC dialogue that will warp the player inside the  Lotus  Boss

FirstBossMap = 350060160

def init():
    sm.setSpeakerID(1540496) # Lotus Hologram
    sm.sendAskYesNo("Are you sure you want to battle me?")

def action(response, answer):
    if response == 1:
        if sm.getParty() is None:
            sm.warpInstanceIn(FirstBossMap)
        else:
            if sm.checkParty():
                sm.warpPartyIn(FirstBossMap)
    else:
        sm.dispose()