# a pile of flowers (1043000) | Forest of Endurance : Stage 2 (910130001)
# Forest of Endurance (Quest 2050)
# Author: Tiger

import random

rewards = [4020005, 4020006, 4020004, 4020001, 4020003, 4020000, 4020002]

if sm.hasQuest(2050):
    response = sm.sendAskYesNo("Are you sure you want to take #bPink Anthurium#k with you?")

    if response:
         sm.giveItem(4031020, 1)
         sm.warp(101000000)
else:
    sm.giveItem(random.choice(rewards), 2)
    sm.warp(101000000)
