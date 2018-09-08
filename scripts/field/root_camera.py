# Swamp : Silent Swamp (105010000)  |  Used for camera movement for the Root Abyss QuestLine

AN_URGENT_SUMMONS = 30000

status = -1
def init():
    if sm.hasQuest(AN_URGENT_SUMMONS):
        sm.lockInGameUI(True)
        sm.moveCamera(170, 400, -850)
        sm.sendDelay(8000) # used to increment the status with 1, after 8000 ms
    else:
        sm.dispose()

def action(response, answer):
    global status
    status += 1

    if status == 0:
        sm.setPlayerAsSpeaker()
        sm.sendNext("Oh, there's a new area.")

    elif status == 1:
        sm.moveCameraBack(20000)
        sm.lockInGameUI(False)