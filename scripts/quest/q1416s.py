#   [Job Adv] (Lv.30)   Way of the Mage IL

darkMarble = 4031013
job = "Mage (Ice, Lightning)"
monster = "Zombie Lupin"
status = -1

def init():
    sm.sendNext("You wish to become a #b"+ job +"#k?\r\n"
                                                "a #b"+ job +"#k is specialised in short range attacks and use #bdaggers#k to defeat their enemies. "
                                                             "There are many useful skills you can acquire.")

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.sendNext("Before I teach you the ways of the "+ job +", you will have to accomplish a very difficult test. "
                                                                "I will warp you into a special map, in which I require you to defeat #b"+ monster +"#k "
                                                                                                                                                    "and return 30 #i"+ str(darkMarble) +"##z"+ str(darkMarble) +"#s to me.")

    if status == 1:
        sm.sendAskYesNo("Once you enter the map, you #rcannot#k return without the #b#t"+ str(darkMarble) +"#s#k, if you die you will lose your experience.\r\n"
                                                                                                           "Are you ready?")

    if status == 2:
        if response == 1:
            sm.warp(910140000, 0) # Magician Test Site
            sm.startQuestNoCheck(parentID)
        else:
            sm.sendSayOkay("You cannot be a Magician forever. You #bwill#k have to face up to the test.\r\n"
                           "Talk to me when you are ready.")
        sm.dispose()