from net.swordie.ms.enums import DimensionalPortalType

def init():
    sm.sendAskSlideMenu(0)

def action(answer, response):
    mapID = DimensionalPortalType.getByVal(response).getMapID()
    if mapID != 0:
        sm.warp(mapID)
    sm.dispose()