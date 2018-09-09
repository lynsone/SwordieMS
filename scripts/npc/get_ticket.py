if parentID == 2012013:
map = 220000100
string = "Ludibrium"
if parentID == 1032008:
map = 200000100
string = "Orbis"
if parentID == 2012021:
map = 240000100
string = "Leafre"
if parentID == 2012001:
map = 104020110
string = "Victoria Island"
if parentID == 2041000:
map = 200000100
string = "Orbis"
if parentID == 2012025:
map = 260000100
string = "Ariant"
if parentID == 2102000:
map = 200000100
string = "Orbis"

response = sm.sendAskYesNo("Would you like to go to " + (string) + "?")


if response == 1:
    sm.warp(map, 0)
sm.dispose()


