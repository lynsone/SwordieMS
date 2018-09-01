# The new me
status = -1

def init():
    sm.setSpeakerID(1061005);
    sm.sendNext("Oh my... I can feel overflowing energy from your body. Do you still remember the rumour about Curbrock?")


def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.sendNext("Recently many people have disappeared from the Broken city. The rumour about Curbrock's return is spreading throughout the city.")

    if status == 1:
        sm.sendAskYesNo("Then, are you ready to defeat #rCurbrock #kright away?")

    if status == 2:
        if response == 1:
            sm.sendNext("Curbrock will only fall to your strongest attack. Good luck!")
        else:
            sm.dispose()

    if status == 3:
        if sm.getParty() is None or sm.getPartySize() > 1:
            sm.sendSayOkay("Please be in a party of 1 to enter the Mu Lung Dojo.")
            sm.dispose()
        else:
            sm.warpPartyIn(925070100) #Dojo Floor 1
            sm.dispose()