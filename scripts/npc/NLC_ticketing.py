#Train trip to/from NLC doesn't display a script#
map = 600010001
string = "New Leaf City"
if sm.getFieldID() == 600010001:
    map = 103000100
    string = "Kerning City"

def init():
    sm.sendAskYesNo("Would you like to take the train to " + (string) + "?")

def action(response,answer):
    if response == 1:
        sm.warp(map, 0)
        sm.dispose()