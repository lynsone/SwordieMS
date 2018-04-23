map = 100000000
if sm.getFieldID() != 951000000:
    map = 951000000


def init():
    sm.sendAskYesNo("Would you like to go to #m" + str(map) + "#?")

def action(response, answer):
    if response == 1:
        sm.warp(map, 0)
    sm.dispose()