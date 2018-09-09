maps = {
    2012013 : [220000100, "Ludibrium"],
    1032008 : [200000100, "Orbis"],
    2012021 : [240000100, "Leafre"],
    2012001 : [104020110, "Victoria Island"],
    2041000 : [220000100, "Orbis"],
    2012025 : [260000100, "Ariant"],
    2102000 : [200000100, "Orbis"],
}

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

if parentID in maps:
    val = maps[parentID]
    if sm.sendAskYesNo("Would you like to go to " + vals[1] + "?"):
        sm.warp(vals[0], 0)
else:
    sm.sendSayOkay("Not coded :(")


