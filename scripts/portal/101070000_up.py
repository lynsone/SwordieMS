# Portal to leave Ellinel Fairy Academy

FANZY = 1040002 # NPC ID
MIDSUMMER_NIGHTS_FOREST_PATH_FROM_ELLINEL = 101074001 # MAP ID

def init():
    sm.setSpeakerID(FANZY)
    sm.sendAskYesNo("Head back to #b North Forest: Giant Tree#k?")

def action(response, answer):
    if response == 1:
        sm.warp(MIDSUMMER_NIGHTS_FOREST_PATH_FROM_ELLINEL)
    sm.dispose()