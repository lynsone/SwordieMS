#Helmsman Tanya (1510006) | All over Riena Strait

portal = {
    141000000 : 1, #Glacial Observatory
    141010000 : 2, #Ice Station 1
    141010400 : 7, #Nora's Cove
    141020000 : 0, #Ice Station 2
    141040000 : 0, #Barbara's House
    141030000 : 0, #Ice Station 3
    141050000 : 0, #Glacier Cutter Base
}


def init():
    sm.sendAskYesNo("Navigator, would you like to set sail?")

def action(response, answer):
    if response == 1:
        sm.warp(141060000, portal[sm.getFieldID()])
    sm.dispose()