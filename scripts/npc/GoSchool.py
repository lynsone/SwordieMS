#Schrodinger (9330189) | Perion
def init():
    sm.sendAskYesNo("Do you wish to visit the #b#m"+ str(744000000) +"##k?")

def action(response, answer):
    if response == 1:
        sm.warp(744000000, 0)
    sm.dispose()