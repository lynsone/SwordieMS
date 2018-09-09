# Introduction quest portal for resistance

ULRIKA_ID = 2191009
VON_ID = 2154016
JUN_ID = 2159430

sm.setSpeakerID(ULRIKA_ID)
sm.sendNext("There you are, #h #! You're late. Get over here.")

sm.chat(str(response))
if response == 1:
else:
    sm.setSpeakerID(VON_ID)
    sm.sendNext("What was the hold up? You took forever.")
    sm.setPlayerAsSpeaker()
    sm.sendSay("Don't be rediculous, it only took me a little while.")
    sm.setSpeakerID(JUN_ID)
    sm.sendSay("Jun says something")
    sm.setSpeakerID(VON_ID)
    sm.sendSay("Von says something")
    sm.setSpeakerID(JUN_ID)
    sm.sendSay("Jun says something")
    sm.setSpeakerID(ULRIKA_ID)
    sm.sendSay("Ulrika says something")
    sm.setPlayerAsSpeaker()
    sm.sendSay("Hide and seek?")
    sm.setSpeakerID(VON_ID)
    sm.sendSay("Ugh, la-ame.")
    sm.setSpeakerID(ULRIKA_ID)
    sm.sendSay("Ulrika says something")
else:
    sm.warp(931000001, 1)
    sm.dispose()

