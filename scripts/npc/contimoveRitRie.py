# Puro (1200004) | Lith Harbor
def init():
    sm.sendAskYesNo("Would you like to go to #b#m"+ str(140000000) +"##k?")

def action(response, answer):
    if response == 1:
        sm.warp(140000000, 0)
    sm.dispose()