# Philius's Request

PHILIUS = 1033202

status = -1
def init():
    sm.setSpeakerID(PHILIUS)
    sm.sendNext("Maybe we're the lucky ones. "
                "While we sleep, Maple World will heal from the terrible things the Black Mage has done. "
                "I wonder what kind of world we'll wake up to?")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.sendAskYesNo("Your Highness, I will dream of a more beautiful world when we awaken...")

    elif status == 1:
        if response == 1:
            sm.completeQuest(parentID)
            sm.sendSayOkay("I wish you...sweet dreams...")
            sm.startQuestNoCheck(24005) # Cursed Slumber
        sm.dispose()