# [Gold Beach] A Golden Opportunity

mapid = 120040300

def init():
    sm.setSpeakerID(9201343)
    sm.sendAskYesNo("Hey there!\r\nI have chosen you to be on the Gold Beach Resort VIP list! "
                    "I'll get my personal plane to pick you up and take you there!"
                    "\r\n\r\n(accepting will warp you)")

def action(response, answer):
    if response == 1:
        sm.completeQuest(parentID)
        sm.warp(mapid, 0)
    sm.dispose()