# The new me

sm.setSpeakerID(1061005);
sm.sendNext("Oh my... I can feel overflowing energy from your body. Do you still remember the rumour about Curbrock?")



    sm.sendNext("Recently many people have disappeared from the Broken city. The rumour about Curbrock's return is spreading throughout the city.")

        response = sm.sendAskYesNo("Then, are you ready to defeat #rCurbrock #kright away?")

        if response == 1:
            sm.sendNext("Curbrock will only fall to your strongest attack. Good luck!")
        else:
            sm.dispose()

        if sm.getParty() is None or sm.getPartySize() > 1:
            sm.sendSayOkay("Please be in a party of 1 to enter the Mu Lung Dojo.")
            sm.dispose()
        else:
            sm.warpPartyIn(925070100) #Dojo Floor 1
            sm.dispose()
