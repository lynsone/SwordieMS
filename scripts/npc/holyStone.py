# Holy Stone - Holy Ground at the Snowfield (3rd job)

def init():
    if sm.hasQuest(1442): # Explorer 3rd job quests TODO Add more
        sm.sendAskYesNo("#b(A mysterious energy surrounds this stone. Do you want to investigate?)")
    else:
        sm.sendSayOkay("#b(A mysterious energy surrounds this stone)#k")
        sm.dispose()

def action(response, answer):
    if response == 1:
        sm.warpInstanceIn(910540000, 0)
    sm.dispose()