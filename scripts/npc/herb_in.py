# Shane (1032003) | Ellinia (101000000)
# The Pink Anthurium (Quest 2050) | The Double-Rooted Reg Ginseng (Quest 2051)
# Author: Tiger

if sm.hasQuest(2050): # Forest of Endurance - The Pink Anthurium
    response = sm.sendAskYesNo("You want my herbs, do you? What kind of farmer would just let people trample over his family land? But... I could use the money. I need at least #r23900#k mesos to feel good about this.")
    if response:
        if sm.getMesos() > 23900: #TODO: calc cost based on lvl, that's how gms does it
            sm.deductMesos(23900)
            sm.warp(910130000)
        else:
            sm.sendSayOkay("Sorry but it doesn't look like you have enough mesos!")
    else:
        sm.sendSayOkay("Alright, see you next time.")

elif sm.hasQuestCompleted(2050) and not sm.hasQuest(2051): # free of charge if completed quest already
    response = sm.sendAskYesNo("Would you like to enter The Forest of Endurance?")
    if response:
        sm.warp(910130000)
    else:
        sm.sendSayOkay("Alright, see you next time.")

elif sm.hasQuest(2051): # Forest of Endurance - The Double-Rooted Reg Ginseng
    response = sm.sendAskYesNo("Back again?! Well, I'm going to need at least #r6200#k mesos if you want me to turn a blind eye. I don't care if you ARE working for #b#p" + str(1103003) + "##k. What do you say?")
    if response:
        if sm.getMesos() > 6200:
            sm.deductMesos(6200)
            sm.warp(910130100)
        else:
            sm.sendSayOkay("Sorry but it doesn't look like you have enough mesos!")

else: # If has never started quest 2050
    response = sm.sendAskYesNo("Hi, i'm Shane. I can let you into the Forest of Patience for a small fee. Would you like to enter for #b5000#k mesos?")
    if response:
        if chr.getLevel() > 25:
            if sm.getMesos() > 5000:
                sm.deductMesos(5000)
                sm.warp(910130000)
            else:
                sm.sendSayOkay("Sorry but it doesn't look like you have enough mesos!")
        else:
            sm.sendSayOkay("You must be a higher level to enter the Forest of Endurance.")
    else:
        sm.sendSayOkay("Alright, see you next time.")
