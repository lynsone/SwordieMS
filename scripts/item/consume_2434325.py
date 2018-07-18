#Scarecrow Summoning Sack (2434325) | Used to spawn a Dojo Dummy on Client's position
from net.swordie.ms.constants import GameConstants
strawDummy = 9305655

def init():
    if not sm.getChr().getField().getReturnMap() == sm.getFieldID():
        sm.chat("You can only spawn a dummy in a Town Map")

    elif sm.mobsPresentInField():
        sm.chat("You cannot spawn a dummy whilst there are other monsters or dummies in the map")

    else:
        sm.spawnMobOnChar(strawDummy)
        sm.removeMobsAfterTimer(strawDummy, GameConstants.DOJO_DUMMY_DURATION * 60) #Template ID & Seconds
        sm.chatBlue("The Training Dummy will be removed after "+ str(GameConstants.DOJO_DUMMY_DURATION) +" minutes.")
        sm.consumeItem(parentID)
    sm.dispose()