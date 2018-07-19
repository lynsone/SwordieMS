#[Commerci Republic] Ciao, Untril Next Time
status = -1
def init():
    sm.setSpeakerID(9390202) #Leon Daniella
    sm.sendNext("Good! Now I leave. I just came to Berry to get fish from Berry. Haha! They have the same name! Berry, Berry, Berry, Berry")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setSpeakerID(0) #Has to be Player Avatar
        sm.sendNext("Do you have to leave right this second? I wanted to ask my new best pal some questions.")

    elif status == 1:
        sm.setSpeakerID(9390202) #Leon Daniella
        sm.sendNext("Oh, shoot! I have to catch a ship. Shoot! Hey! Why don't you come to #bSan Commerci#k? You can meet my dad! He's really cool.")

    elif status == 2:
        sm.setSpeakerID(0) #Has to be Player Avatar
        sm.sendNext("Berry did suggest I visit.")

    elif status == 3:
        sm.setSpeakerID(9390202) #Leon Daniella
        sm.sendAskYesNo("Yeah! Just go to San Commerci and wait for me. I have a couple of voyages to make, but don't leave until I get back. Promise?")

    elif status == 4:
        if response == 1:
            sm.startQuestNoCheck(parentID)
            sm.sendSayOkay("Okay, go east from the town, follow the coastal road, and you'll get to San Commerci. It's a single road, but I get lost on it all the time. \r\n"
                           "See you in #e#bSan Commerci#k#n. Remember, you promised!")
        else:
            sm.sendSayOkay("You're not gonna promise your best friend a simple thing?")
        sm.dispose()