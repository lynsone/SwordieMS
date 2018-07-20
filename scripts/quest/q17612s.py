# [Commerci Republic] The Problem with Presumptions

status = -1
def init():
    sm.setSpeakerID(0) # Has to be Player Avatar
    sm.sendNext("Excuse me, Mayor? A-are you busy?")

def action(response, answer):
    global status, selection
    status += 1

    if status == 0:
        sm.setSpeakerID(9390201) # Mayor Berry
        sm.sendNext("You stopped by at just the right time, kiddo. "
                    "It'd been a fishful day, and that's the best kinda day, if you ask me.")

    elif status == 1:
        sm.setSpeakerID(0) # Has to be Player Avatar
        sm.sendNext("Oh, great! That's... I need to tell you something.")

    elif status == 2:
        sm.setSpeakerID(9390201) # Mayor Berry
        sm.sendNext("Well, go on and spit it out!\r\n"
                    "\r\n"
                    "#L0##b(I should rethink this.)#l\r\n"
                    "#L1##b(I've got to tell him the truth.)#l")

    elif status == 3:
        selection = answer
        sm.setSpeakerID(0) # Has to be Player Avatar
        if selection == 0:
            sm.sendNext("Oh, I just wanted to tell you what a beautiful day it is")
        elif selection == 1:
            sm.sendNext("The truth is... I lied to you. The sea didn't bring me here...")

    elif status == 4:
        sm.setSpeakerID(9390201) # Mayor Berry
        if selection == 0:
            sm.sendNext("Oh, ye. It truly is a gorgeous day, today")
            sm.dispose()
        elif selection == 1:
            sm.sendNext("Well, unless you've got a set of wings curled up somewhere, "
                        "how'd you get here? And what do you mean you lied?")

    elif status == 5:
        sm.setSpeakerID(0) # Has to be Player Avatar
        sm.sendNext("I came across the northern barrier.")

    elif status == 6:
        sm.setSpeakerID(9390201) # Mayor Berry
        sm.sendNext("What kinda hogwash is that? Nobody's been across the northern barrier in a hound's age. "
                    "Ain't nothin' but demons and evil up there anyway. "
                    "You sayin' you're some kinda demon?!")

    elif status == 7:
        sm.setSpeakerID(0) # Has to be Player Avatar
        sm.sendNext("No, no... the demons are... Look, I'm from a place called Maple World, and I guess technically you are too. "
                    "I traveled to Dawnveil from a different continent, far away.")

    elif status == 8:
        sm.setSpeakerID(9390201) # Mayor Berry
        sm.sendNext("Well that's just plum silly. Another continent? "
                    "I was sure this sea just went on forever... "
                    "If this is all true, how'd you get across that barrier?")

    elif status == 9:
        sm.setSpeakerID(0) # Has to be Player Avatar
        sm.sendNext("The barrier is breaking down... Where I came from, no one had even heard of Dawnveil, "
                    "and our leaders feared it might be a danger to the rest of Maple World.")

    elif status == 10:
        sm.sendNext("I was sent here to explore, investigate and to see if our two peoples could have a peace.")

    elif status == 11:
        sm.setSpeakerID(9390201) # Mayor Berry
        sm.sendNext("Well, I'll be a toad on a hot plate. I don't much care for bein' on the other end of a lie, "
                    "but I can tell you wasn't tryin' to cause no trouble.")

    elif status == 12:
        sm.setSpeakerID(0) # Has to be Player Avatar
        sm.sendNext("Thanks... I'm really sorry.")

    elif status == 13:
        sm.setSpeakerID(9390201) # Mayor Berry
        sm.sendNext("Let's let bygones go on down bygone way. Can I help you with your plan?")

    elif status == 14:
        sm.setSpeakerID(0) # Has to be Player Avatar
        sm.sendNext("Maybe you can... Your people really seem to be about the same as ours, "
                    "but I think the fear of what's beyond the barrier could stir up trouble. "
                    "They might perceive me as a threat...")

    elif status == 15:
        sm.setSpeakerID(9390201) # Mayor Berry
        sm.sendNext("Well, people ain't always kind to the ones that come from afar, "
                    "and changing minds one by one will get you nowhere fast. I've got me another plan.")

    elif status == 16:
        sm.setSpeakerID(0) # Has to be Player Avatar
        sm.sendNext("Yes?")

    elif status == 17:
        sm.setSpeakerID(9390201) # Mayor Berry
        sm.sendNext("Go on down to the docks. "
                    "There's a ship there that belongs to the Prime Minister of #bSan Commerci#k, a fella named #e#bGilberto Daniella#k#n. "
                    "His boy, #e#bLeon#k#n, is the captain of the ship. "
                    "They're good folk, and they hold a lot of sway.")

    elif status == 18:
        sm.sendNext("You show those Daniellas you're worth trustin', and you might just have yourself a strong supporter in the richest nation of Dawnveil.")

    elif status == 19:
        sm.setSpeakerID(0) # Has to be Player Avatar
        sm.sendNext("You're a lot smarter than I took you for. I'm sorry for underestimating you. "
                    "I don't know how I can thank you...")

    elif status == 20:
        sm.setSpeakerID(9390201) # Mayor Berry
        sm.sendNext("You've done right by me and mine. You just do what's best for all our people, "
                    "and I'll call that thanks enough. Now go on, get, before I have to get my broom and shoo you off! "
                    "Find #e#bLeon Daniella#k#n in the guest house on the east end of town.")

    elif status == 21:
        sm.sendNext("And take this! It ain't much, but think of it as a souvenir!")

    elif status == 22:
        sm.sendNext("Don't get shy, just take it!")

    elif status == 23:
        sm.startQuestNoCheck(parentID)
        sm.giveItem(1003984) # Commerci Hat
        sm.completeQuest(parentID)
        sm.dispose()