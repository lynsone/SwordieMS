#Puro (1200003) | Snow Island : Penguin Port
def init():
    sm.sendAskYesNo("Would you like to go to #b#m"+ str(104000000) +"##k?")

def action(response, answer):
    if response == 1:
        sm.warp(104000000, 0)
    sm.dispose()