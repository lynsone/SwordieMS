# Introduction quest portal for resistance

ULRIKA_ID = 2191009
VON_ID = 2154016
JUN_ID = 2159430
status = -1

def init():
    sm.setSpeakerID(ULRIKA_ID)
    sm.sendNext("There you are, #h #! You're late. Get over here.")

def action(response, answer):
    sm.chat(str(response))
    global status
    if response == 1:
        status += 1
    else:
        status -= 1
    if status == 0:
        sm.setSpeakerID(VON_ID)
        sm.sendNext("What was the hold up? You took forever.")
    elif status == 1:
        sm.setPlayerAsSpeaker()
        sm.sendSay("Don't be rediculous, it only took me a little while.")
    elif status == 2:
        sm.setSpeakerID(JUN_ID)
        sm.sendSay("Jun says something")
    elif status == 3:
        sm.setSpeakerID(VON_ID)
        sm.sendSay("Von says something")
    elif status == 4:
        sm.setSpeakerID(JUN_ID)
        sm.sendSay("Jun says something")
    elif status == 5:
        sm.setSpeakerID(ULRIKA_ID)
        sm.sendSay("Ulrika says something")
    elif status == 6:
        sm.setPlayerAsSpeaker()
        sm.sendSay("Hide and seek?")
    elif status == 7:
        sm.setSpeakerID(VON_ID)
        sm.sendSay("Ugh, la-ame.")
    elif status == 8:
        sm.setSpeakerID(ULRIKA_ID)
        sm.sendSay("Ulrika says something")
    else:
        sm.warp(931000001, 1)
        sm.dispose()

