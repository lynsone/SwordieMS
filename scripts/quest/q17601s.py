# [Commerci Republic] In the Name of the Empress

status = -1
sm.setSpeakerID(1064023) # Cygnus
def init():
    sm.sendNext("Ah #b#h0##k you've come! My apologies for bringing you here on such short notice. Tell me, have you heard of the land of Commerci, by any chance?")
    if not sm.hasQuest(parentID):
        sm.startQuestNoCheck(parentID)

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.sendNext("Commerci was once a small fishing village, and they had little in the way of dealings with the rest of the world. "
                    "It seems that time has been good to Commerci, as they have begun sending large trading vessels around the globe,"
                    "and have re-branded themselves the #bCommerci Republic#k.")

    elif status == 1:
        sm.sendNext("As empress, I fear that such rapid growth from Commerci may have adverse effects on the rest of our world. "
                    "If we can earn their friendship, they may become a powerful ally in time. "
                    "However, if Commerci were to side with Black Mage... they could become a formidable threat.")

    elif status == 2:
        sm.sendAskYesNo("#h0#, we need your help. Please act as my envoy to Commerci, and bring them a message: "
                    "The kingdom of Ereve seeks a peaceful and mutually beneficial relationship with Commerci. "
                    "Will you please do this for me? Neinheart will explain the mission.")

    elif status == 3:
        if response == 1:
            sm.sendNext("I know you won't let me down.")
            sm.completeQuest(parentID)
        else:
            sm.sendSayOkay("Please, let me know when you are willing to accept the mission. but make up your mind soon, time is of the essence.")
        sm.dispose()