# [Commerci Republic] Delfinos? More like dead Fishos
from net.swordie.ms.constants import WzConstants

COMMERCI_SUIT = 1052673
expGiven = 530255
status = -1
def init():
    sm.setPlayerAsSpeaker() # Has to be Player Avatar
    sm.sendNext("I'm not hinting that you're weak, prime minister. I just want to support you, as a friend of #bLeon#k.")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setSpeakerID(9390203) # Gilberto Daniella
        sm.sendNext("That's fine.")

    if status == 1:
        sm.sendNext("Take these as well, they will be helpful.\r\n\r\n"
                    ""+ WzConstants.ICON_OBTAINED +"\r\n"
                    "#v"+ str(COMMERCI_SUIT) +"##z"+ str(COMMERCI_SUIT) +"#\r\n\r\n"
                    ""+ WzConstants.ICON_EXP +"\r\n"
                    ""+ str(expGiven) +" exp")

    if status == 2:
        sm.giveExp(expGiven) # Give Exp
        sm.giveItem(COMMERCI_SUIT) # Give Commerci Suit
        sm.completeQuest(parentID)
        sm.dispose()