# [Commerci Republic] The Minister's Son

status = -1
def init():
    sm.setSpeakerID(9390202) # Leon Daniella
    sm.sendNext("I'm... fine, you... meddling dumb-dumb!")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("#b(He's not very polite...)#k\r\n"
                    "Er, you're Leon Daniella, right? You all right? Those cats really did a number on you.")

    elif status == 1:
        sm.setSpeakerID(9390202) # Leon Daniella
        sm.sendNext("You idiot, I... said I was-- Hold up.. You know my name? "
                    "Are you that noble I sent money to via that foreign bank account? "
                    "You promised me a present!")

    elif status == 2:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("Um, no. I just heard those cats say your name. I was actually on my way to see you, though.")

    elif status == 3:
        sm.setSpeakerID(9390202) # Leon Daniella
        sm.sendNext("Whoa, whoa, whoa.. Are you from the #b#eHeaven Empire#k#n? Not cool.")

    elif status == 4:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("The what empire? Look, I'm not from around here. "
                    "I'm from Maple World, across the barrier. "
                    "I'm here to establish good relations for Empress Cygnus.")

    elif status == 5:
        sm.setSpeakerID(9390202) # Leon Daniella
        sm.sendNext("So you ARE from the Heaven Empire? Wait.. did you say barrier? Stay away from me, demon! Hyah! Hyah!")

    elif status == 6:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("#b(Demon? Ah, they must think demons live on the other side of the barrier.)\r\b#k"
                    "I'm not a demon. Um, look, I can stand in sunlight. And the garlic you're throwing at me is having no effect.")

    elif status == 7:
        sm.setSpeakerID(9390202) # Leon Daniella
        sm.sendNext("Oh, whew. You're right! Haha! That was a close one.")

    elif status == 8:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("#b(I can't believe that worked...)\r\n#k"
                    "I'm from a world very similar to yours. A place called #e#rMaple World#k#n.")

    elif status == 9:
        sm.setSpeakerID(9390202) # Leon Daniella
        sm.sendNext("In that case, you can be my best friend sidekick. "
                    "Pat yourself on the back. "
                    "Leon Daniella doesn't befriend just anyone! "
                    "Now you pat my back, and I'll pat yours. "
                    "There, doesn't that feel nice?")

    elif status == 10:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("#b(Great, I'm becoming vast friends with Leon.)")
        sm.completeQuest(parentID)
        sm.dispose()