#Manji (1022002) | Perion
def init():
    if sm.getChr().getLevel() < 50:
        sm.sendSayOkay("Leave now.. before you get hurt.")
        sm.dispose()
    else:
        sm.sendAskYesNo("You appear strong. Would you like to head to the Balrog Temple?")

def action(response, answer):
    if response == 1:
        sm.warp(105100100, 0)
    sm.dispose()