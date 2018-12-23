# Baby Pig  (1013200) |
#todo remove npc
if sm.canHold(4032449):
    sm.giveItem(4032449)
    sm.dispose()
else:
    sm.sendSay("Please make room in your Etc Inventory.")
    sm.dispose()