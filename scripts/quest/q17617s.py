# [Commerci Republic] Missing Goods

status = -1
def init():
    sm.setSpeakerID(9390220) # Maestra Fiametta
    sm.sendNext("The only odd thing about it was...")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendAskYesNo("#b(Aha! I knew there had to be something!)")

    elif status == 1:
        if response == 1:
            sm.setPlayerAsSpeaker() # Has to be Player Avatar
            sm.sendNext("Yes?")
        else:
            sm.dispose()

    elif status == 2:
        sm.setSpeakerID(9390220) # Maestra Fiametta
        sm.sendNext("The items Tepes deposited were obviously packed for sea, but Tepes brought a cart with him when he picked them up, "
                    "Usually the marchants move them straight to the ships...")

    elif status == 3:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("Did you see which way he went?")

    elif status == 4:
        sm.setSpeakerID(9390220) # Maestra Fiametta
        sm.sendNext("Listen, kid. I don't keep track of every sailor who walks by. But there was a fish cart accident between here and Berry, so the western path is blocked.")

    elif status == 5:
        sm.setSpeakerID(9390220) # Maestra Fiametta
        sm.sendNext("And if he didn't go west, he probably went east, towards the canals")

    elif status == 6:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("Alright, thank you so much for the information.")

    elif status == 7:
        sm.setSpeakerID(9390220) # Maestra Fiametta
        sm.sendNext("Whatever, kid. Just don't mess with my trading post.")
        sm.startQuestNoCheck(parentID)
        sm.completeQuest(parentID)
        sm.chatScript("Return the stolen goods to Tepes in San Commerci")
        sm.chatScript("You found the impostor at the eastern canals, when confronted, a Robed Lady appeared. But she fled.")
        sm.startQuestNoCheck(17619) # [Commerci Republic] Come Back Here!
        sm.dispose()