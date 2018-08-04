# [Commerci Republic] Come Back Here!

status = -1
def init():
    sm.setSpeakerID(9390225) # Tepes
    sm.sendNext("I can't believe it. All those items... stolen! And the iron I ordered was in there too")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("Hey Tepes!")

    elif status == 1:
        sm.setSpeakerID(9390225) # Tepes
        sm.sendNext("And the white carnation buttons I was going to sew on my vest were in there too... I can't believe they're all gone.")

    elif status == 2:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("#b(Aww, he looks seriously depressed.)")

    elif status == 3:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("Hellooooo I got your items back. See?")

    elif status == 4:
        sm.setSpeakerID(9390225) # Tepes
        sm.sendNext("And the silver ribbons I ordered to tie my-- Wait, what? You got my items! Oh, happy day! How'd you do it?")

    elif status == 5:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("It was easy. I tracked down the thief.")

    elif status == 6:
        sm.setSpeakerID(9390225) # Tepes
        sm.sendNext("Incredible. That'll teach me to judge people based on their clothing.")

    elif status == 7:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("#b(Hey! What did he mean by that?!)")

    elif status == 8:
        sm.setSpeakerID(9390225) # Tepes
        sm.sendNext("Anyway. I owe you one, my friend. Anything you need, I'll make it happen. I swear it.")

    elif status == 9:
        sm.setPlayerAsSpeaker() # Has to be Player Avatar
        sm.sendNext("Are you serious?")

    elif status == 10:
        sm.setSpeakerID(9390225) # Tepes
        sm.sendNext("On my very life. Whatever you need.")
        sm.completeQuest(parentID)
        sm.dispose()