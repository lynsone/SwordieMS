# 25825 - [Job Advancement] Agent of Justige (AB 2nd job adv)
from net.swordie.ms.loaders import ItemData

status = -1
ESKALADE_NPC_ID = 3000132

def init():
    if chr.getLevel() >= 60 and chr.getJob() == 6510:
        sm.setPlayerAsSpeaker()
        sm.sendNext("Eskalade, how come all my skills are getting... pinker?")
    else:
        sm.dispose()

def action(response, answer):
    sm.setSpeakerID(ESKALADE_NPC_ID)
    global status
    status += 1
    if status % 2 == 1:
        sm.setPlayerAsSpeaker()
    if status == 0:
        sm.sendNext("Well, it IS my favorite color. Maybe it means you're getting better at using my power.")
    elif status == 1:
        sm.sendNext("Wait, your favorite color is pink? Why do I get the only dragon who loves cutesy things?")
    elif status == 2:
        sm.sendNext("This whole thing would be a lot easier if you just gave in to my supreme will and played along.")
    elif status == 3:
        sm.sendNext("You seriously can't make the pink go away?")
    elif status == 4:
        sm.sendNext("Nope! You can just deal with it. Besides, it's a good color for you. Brings out your rosy cheeks."
                    "Now, do you want to synchronize souls again?")
    elif status == 5:
        sm.sendNext("Yeah, I guess so... I'll get stronger, right?")
    elif status == 6:
        sm.sendNext("Absolutely! You will become my genuine pink angel.")
    elif status == 7:
        sm.sendNext("I really don't know about this...")
    elif status == 8:
        sm.sendAskYesNo("You have to make sacrifices to be a hero! Don't you want that?")
    elif status == 9:
        if response == 1:
            sm.sendNext("I think I just got stronger!")
        else:
            sm.dispose()
    else:
        sm.setJob(6511)
        sm.addSP(5)
        sm.addAP(5)
        equippedInv = chr.getEquippedInventory()
        oldSecondary = equippedInv.getItemBySlot(10)
        chr.consumeItem(oldSecondary)
        secondary = ItemData.getItemDeepCopy(1352603)
        secondary.setBagIndex(10)
        chr.getAvatarData().getAvatarLook().getHairEquips().add(secondary.getItemId())
        chr.setSpToCurrentJob(5)
        chr.getEquippedInventory().addItem(secondary)
        secondary.updateToChar(chr)
        sm.dispose()

