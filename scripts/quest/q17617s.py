# [Commerci Republic] Missing Goods

sm.setSpeakerID(9390220) # Maestra Fiametta
sm.sendNext("The only odd thing about it was...")


sm.setPlayerAsSpeaker() # Has to be Player Avatar
response = sm.sendAskYesNo("#b(Aha! I knew there had to be something!)")

if response == 1:
    sm.setPlayerAsSpeaker() # Has to be Player Avatar
    sm.sendNext("Yes?")
else:
    sm.dispose()

sm.setSpeakerID(9390220) # Maestra Fiametta
sm.sendNext("The items Tepes deposited were obviously packed for sea, but Tepes brought a cart with him when he picked them up, "
            "Usually the marchants move them straight to the ships...")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Did you see which way he went?")

sm.setSpeakerID(9390220) # Maestra Fiametta
sm.sendNext("Listen, kid. I don't keep track of every sailor who walks by. But there was a fish cart accident between here and Berry, so the western path is blocked.")

sm.setSpeakerID(9390220) # Maestra Fiametta
sm.sendNext("And if he didn't go west, he probably went east, towards the canals")

sm.setPlayerAsSpeaker() # Has to be Player Avatar
sm.sendNext("Alright, thank you so much for the information.")

sm.setSpeakerID(9390220) # Maestra Fiametta
sm.sendNext("Whatever, kid. Just don't mess with my trading post.")
sm.startQuestNoCheck(parentID)
sm.completeQuest(parentID)
sm.chatScript("Return the stolen goods to Tepes in San Commerci")
sm.chatScript("You found the impostor at the eastern canals, when confronted, a Robed Lady appeared. But she fled.")
sm.startQuestNoCheck(17619) # [Commerci Republic] Come Back Here!
sm.dispose()
