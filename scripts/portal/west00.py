fields = {
    # fromField : [toField, portal]
    106030200 : [106030300, 2],
    401060000 : [401053002, 2],
    865020000 : [865000000, 10],
    865020100 : [865020000, 4],
    863010420 : [863010500, 5],
    863010500 : [863010320, 1],
    863010330 : [863010500, 0],
    101050000 : [101050100, 2],
    272000000 : [270000000, 6],
    272000600 : [272000500, 2],
    272010000 : [272000600, 2],
    272010200 : [272010100, 2],
    106030211 : [106030210, 2],
    130030001 : [130030002, 0],
    130030002 : [130030003, 0],
    130030003 : [130030004, 0],
    130030004 : [130030005, 0],
    100040000 : [100020200, 6],
    931000310 : [931000320, 0],
    910150001 : [910150002, 2], # FFF : Elluel -> FFF : Path of the Glowcaves   *FFF = Frozen Fairy Forest
    211042402 : [211042300, 0],
    211042400 : [211042300, 0],
    211042401 : [211042300, 0],

}


def init():
    warp = True
    fieldID = sm.getFieldID()

    if fieldID == 350060160: # Black Heaven Core (Lotus Stage 1)
        warp = False
        sm.teleportInField(2) #portal Id
        sm.dispose()

    elif fieldID not in fields:
        sm.chat("(portal) This script (west00.py) is not coded for this map. (ID: " + str(fieldID) + ")")
        sm.dispose()
        warp = False

    if warp:
        sm.warp(fields[fieldID][0], fields[fieldID][1])
        sm.dispose()