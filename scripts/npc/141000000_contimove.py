#Puro (1511001) | Glacial Observatory
def init():
    sm.sendAskYesNo("You want to head back to Rien?")

def action(response, answer):
    if response == 1:
        sm.warp(140000000,0)
    sm.dispose()