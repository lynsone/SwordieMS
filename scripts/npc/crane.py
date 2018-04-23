map = 200090300
string = "Mu Lung?"
if sm.getFieldID() == 250000100:
    map = 200090310
    string = "Orbis?"
def init():
    sm.sendAskYesNo("Would you like to go to " + (string))

def action(response, answer):
    if response == 1:
        sm.warp(map, 0)
    sm.dispose()