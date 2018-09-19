from net.swordie.ms.enums import DimensionalPortalType

response = sm.sendAskSlideMenu(0)

mapID = DimensionalPortalType.getByVal(response).getMapID()
if mapID != 0:
    sm.warp(mapID)
