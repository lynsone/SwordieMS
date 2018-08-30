# Holy Stone - Holy Ground at the Snowfield (3rd job)

questIDs = [1431, 1432, 1433, 1435, 1436, 1437, 1439, 1440, 1442, 1443, 1445, 1446, 1447, 1448]

def init():
    hasQuest = False
    for qid in questIDs:
        if sm.hasQuest(qid):
            hasQuest = True
            break
    if hasQuest:
        sm.sendAskYesNo("#b(A mysterious energy surrounds this stone. Do you want to investigate?)")
    else:
        sm.sendSayOkay("#b(A mysterious energy surrounds this stone)#k")
        sm.dispose()

def action(response, answer):
    if response == 1:
        sm.warpInstanceIn(910540000, 0)
    sm.dispose()