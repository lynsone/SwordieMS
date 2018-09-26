# a pile of herbs (1043001) | Forest of Endurance : Stage 5 (910130102)
# Forest of Endurance - The Double-Rooted Reg Ginseng (Quest 2051)
# Author: Tiger

import random

rewards = [[4020007, 2], [4020008, 2], [4010006, 2], [1032013, 1]]

if sm.hasQuest(2051):
    response = sm.sendAskYesNo("Are you sure you want to take #bDouble-Rooted Red Ginseng#k with you?")

    if response:
         sm.giveItem(4031032, 1)
         sm.warp(101000000)
else:
    rand = random.choice(rewards)
    sm.giveItem(rand[0], rand[1])
    sm.warp(101000000)
