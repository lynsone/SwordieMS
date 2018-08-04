# [Commerci Republic] Delfino Deleter 5

status = -1
def init():
    sm.setSpeakerID(9390256) # Leon Daniella
    sm.sendNext("I keep thinking I see a Delfino out of the corner of my... WHAT THE?!")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("It's the Delfinos! They're back!")

    elif status == 1:
        sm.setSpeakerID(9390256) # Leon Daniella
        sm.sendNext("Who's that scary-looking fish behind them?")

    elif status == 2:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("He's bad news, that's who!")

    elif status == 3:
        sm.setSpeakerID(9390208) # Riverson
        sm.sendNext("Why you attack my people?")

    elif status == 4:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("What the...? You guys attacked the Merchant Union first! Who ARE you, anyway?")

    elif status == 5:
        sm.setSpeakerID(9390208) # Riverson
        sm.sendNext("I am... RIVERSON! you pay for wrongdoing!")

    elif status == 6:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("Wrongdoing? What did we do?")

    elif status == 7:
        sm.setSpeakerID(9390208) # Riverson
        sm.sendAskYesNo("No play coy! You know what you did! No more talk, prepare for PAIN!")

    elif status == 8:
        if response == 1:
            sm.startQuest(parentID)
            sm.completeQuest(parentID)
            sm.completeQuest(17631) # Finish a cutscene quest
            sm.warpInstanceIn(865020061)
        else:
            sm.sendSayOkay("... Oh... Okay... Uhh... I will wait for a bit!")
        sm.dispose()