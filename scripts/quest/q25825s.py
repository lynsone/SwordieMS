# 25825 - [Job Advancement] Agent of Justige (AB 2nd job adv)
from net.swordie.ms.loaders import ItemData

status = -1
ESKALADE_NPC_ID = 3000018

def init():
    if chr.getLevel() >= 30 and chr.getJob() == 6500:
        sm.setSpeakerID(ESKALADE_NPC_ID)
        sm.sendNext("Looking pretty tough there, #h #.")
    else:
        sm.dispose()

def action(response, answer):
    sm.setSpeakerID(ESKALADE_NPC_ID)
    global status
    status += 1
    if status == 0:
        sm.setPlayerAsSpeaker()
        sm.sendNext("Yeah? I'm totally rocking this Heroine of Justice thing.")
    elif status == 1:
        sm.sendNext("Have you noticed feeling a lot closer to me lately? Our pact has been getting stronger and stronger...")
    elif status == 2:
        sm.setPlayerAsSpeaker()
        sm.sendNext("Uh, is that good?.")
    elif status == 3:
        sm.sendNext("What could be bad about getting closer to your dragon-lord? We need to move and act as one.")
    elif status == 4:
        sm.setPlayerAsSpeaker()
        sm.sendNext("That sounds complicated... and gross. What are you getting at?")
    elif status == 5:
        sm.sendNext("I'm trying to let you use my muscle more! You never trust me...")
    elif status == 6:
        sm.setPlayerAsSpeaker()
        sm.sendNext("I could use some more strength!")
    elif status == 7:
        sm.sendAskYesNo("I knew you'd be swayed by a little extra power. Let's strengthen our bond.")
    elif status == 8:
        if response == 1:
            sm.sendNext("Now focus!")
        else:
            sm.sendSayOkay("What happened to wanting more power?")
            sm.dispose()
    elif status == 9:
        sm.setJob(6510)
        sm.addSP(5)
        sm.addAP(5)
        sm.dispose()
        sm.completeQuest(25825)
        # Replace the old secondary with the new one
        equippedInv = chr.getEquippedInventory()
        oldSecondary = equippedInv.getItemBySlot(10)
        chr.consumeItem(oldSecondary)
        secondary = ItemData.getItemDeepCopy(1352602)
        secondary.setBagIndex(10)
        chr.getAvatarData().getAvatarLook().getHairEquips().add(secondary.getItemId())
        chr.setSpToCurrentJob(5)
        chr.getEquippedInventory().addItem(secondary)
        secondary.updateToChar(chr)
    else:
        sm.dispose()
