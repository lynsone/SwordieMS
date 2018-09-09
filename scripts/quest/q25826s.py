# 25825 - [Job Advancement] Agent of Justige (AB 2nd job adv)
from net.swordie.ms.loaders import ItemData

ESKALADE_NPC_ID = 3000132

if chr.getLevel() >= 60 and chr.getJob() == 6510:
    sm.setPlayerAsSpeaker()
    sm.sendNext("Eskalade, how come all my skills are getting... pinker?")
else:
    sm.dispose()

sm.setSpeakerID(ESKALADE_NPC_ID)
    sm.setPlayerAsSpeaker()
    sm.sendNext("Well, it IS my favorite color. Maybe it means you're getting better at using my power.")
    sm.sendNext("Wait, your favorite color is pink? Why do I get the only dragon who loves cutesy things?")
    sm.sendNext("This whole thing would be a lot easier if you just gave in to my supreme will and played along.")
    sm.sendNext("You seriously can't make the pink go away?")
    sm.sendNext("Nope! You can just deal with it. Besides, it's a good color for you. Brings out your rosy cheeks."
                "Now, do you want to synchronize souls again?")
    sm.sendNext("Yeah, I guess so... I'll get stronger, right?")
    sm.sendNext("Absolutely! You will become my genuine pink angel.")
    sm.sendNext("I really don't know about this...")
    response = sm.sendAskYesNo("You have to make sacrifices to be a hero! Don't you want that?")
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

