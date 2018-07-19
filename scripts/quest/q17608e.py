#[Commerci Republic] After a Pleasant Voyage

status = -1
def init():
    sm.setSpeakerID(0) #Has to be Player Avatar
    sm.sendNext("Excuse me... Hi. Could I ask you a question?")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setSpeakerID(9390201) #Mayor Berry
        sm.sendNext("Dear me, you look about as healthy as a gutted guppy! Oh I don't mean no insult. "
                    "You run into a bit of weather out there? "
                    "You should thank your lucky stars you landed here in one piece!")

    elif status == 1:
        sm.setSpeakerID(0) #Has to be Player Avatar
        sm.sendNext("I would like to know where I landed, where am I?")

    elif status == 2:
        sm.setSpeakerID(9390201) #Mayor Berry
        sm.sendNext("Oh, you landed in the Commerci Republic, a gorgeous place!")

    elif status == 3:
        sm.setSpeakerID(0) #Has to be Player Avatar
        sm.sendNext("I landed in the Commerci Republic? This is the Commerci Republic?")

    elif status == 4:
        sm.setSpeakerID(9390201) #Mayor Berry
        sm.sendNext("Yes, that's correct. You landed in the Commerci Republic. As a matter of fact, I'm the mayor of the Commerci Republic.")

    elif status == 5:
        sm.setSpeakerID(0) #Has to be Player Avatar
        sm.sendNext("#b(This little fishing village is the Commerci Republic? I guess there's no truth to the rumors.)")

    elif status == 6:
        sm.setSpeakerID(9390201) #Mayor Berry
        sm.sendNext("You seem quite strong, having survived that ship wreckage. "
                    "Once you've gotten your energy back, you reckon you could help me out with some things? "
                    "I would certainly appreciate it")

    elif status == 7:
        sm.setSpeakerID(0) #Has to be Player Avatar
        sm.sendNext("Really? But we've just met.. you're going to trust me just like that?")

    elif status == 8:
        sm.setSpeakerID(9390201) #Mayor Berry
        sm.sendNext("With age comes wisdom, youngster. I can tell you's a good person just by usin' these old peepers. Now enjoy yourself for now! "
                    "Let me know if there's anything you need.")

    elif status == 9:
        sm.setSpeakerID(0) #Has to be Player Avatar
        sm.sendNext("#b(...He seems nice enough, This person claims to be the Mayor, "
                    "so I guess I could deliver the Empress's message to him. "
                    "I'll wait to make sure he really trusts me before I bring up the peace treaty.")
        sm.completeQuest(parentID)
        sm.dispose()